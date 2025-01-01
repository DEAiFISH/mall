package com.deaifish.mall.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deaifish.mall.config.JWTProperties;
import com.deaifish.mall.pojo.JwtUser;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/31 15:34
 */
@Data
@Slf4j
@Component
public class JWTUtil {
    @Resource
    private JWTProperties jwtProperties;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成用户token,设置token超时时间
     * @param user
     * @return
     */
    public String createToken(JwtUser user) {
        Assert.notNull(user, "用户信息不能为空");

        String username = user.getUsername();

        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // 提取权限名称
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                //可以将基本信息放到claims中
                .withClaim("username", username)
                .withClaim("password", user.getPassword())
                .withClaim("authorities", authorities)
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));

        //保存到redis
        this.saveJwtToRedis(username, token);

        return token;
    }

    /**
     * 校验token并解析token
     * @param token
     * @return
     */
    public Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        //校验token是否正确
        String username = jwt.getClaim("username").asString();
        // 校验 Redis 中是否存在该用户的 JWT
        String redisToken = getJwtFromRedis(username);
        if (StrUtil.isBlank(redisToken) || !token.equals(redisToken)) {
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 将 JWT 保存到 Redis
     * @param username
     * @param jwt
     */
    public void saveJwtToRedis(String username, String jwt) {
        // Redis key 的前缀
        String key = "JWT:" + username;
        stringRedisTemplate.opsForValue().set(key, jwt, jwtProperties.getExpiration(), TimeUnit.SECONDS);
    }

    /**
     * 从 Redis 获取 JWT
     * @param username
     * @return
     */
    public String getJwtFromRedis(String username) {
        String key = "JWT:" + username;
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除 Redis 中的 JWT
     * @param username
     */
    public void deleteJwtFromRedis(String username) {
        String key = "JWT:" + username;
        stringRedisTemplate.delete(key);
    }

}
