package com.deaifish.mall.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deaifish.mall.config.JWTProperties;
import com.deaifish.mall.pojo.bo.JwtUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@RequiredArgsConstructor
public class JWTUtil {
    private final JWTProperties jwtProperties;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 生成用户token,设置token超时时间
     * @param user
     * @return
     */
    public String createToken(JwtUser user) {
        Assert.notNull(user, "用户信息不能为空");

        String wxId = user.getWxId();

        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // 提取权限名称
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                //可以将基本信息放到claims中
                .withClaim("userId", user.getUserId())
                .withClaim("wxId", wxId)
                .withClaim("password", user.getPassword())
                .withClaim("authorities", authorities)
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));

        //保存到redis
        this.saveJwtToRedis(wxId, token);

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
        String wxId = jwt.getClaim("wxId").asString();
        // 校验 Redis 中是否存在该用户的 JWT
        String redisToken = getJwtFromRedis(wxId);
        if (StrUtil.isBlank(redisToken) || !token.equals(redisToken)) {
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 将 JWT 保存到 Redis
     * @param wxId
     * @param jwt
     */
    public void saveJwtToRedis(String wxId, String jwt) {
        // Redis key 的前缀
        String key = "JWT:" + wxId;
        stringRedisTemplate.opsForValue().set(key, jwt, jwtProperties.getExpiration(), TimeUnit.SECONDS);
    }

    /**
     * 从 Redis 获取 JWT
     * @param wxId
     * @return
     */
    public String getJwtFromRedis(String wxId) {
        String key = "JWT:" + wxId;
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除 Redis 中的 JWT
     * @param wxId
     */
    public void deleteJwtFromRedis(String wxId) {
        String key = "JWT:" + wxId;
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除 Redis 中的 JWT，通过 token 解析 wxId
     * @param token
     */
    public void deleteTokenFromRedis(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String wxId = jwt.getClaim("wxId").asString();
        log.info("退出登录，wxId:{}", wxId);
        deleteJwtFromRedis(wxId);
    }

    public JwtUser parseToken(Map<String, Claim> userData) {
        Long userId = userData.get("userId").asLong();
        String wxId = userData.get("wxId").asString();
        String password = userData.get("password").asString();
        List<String> roles = userData.get("authorities").asList(String.class);

        // 将每个角色字符串转换为 SimpleGrantedAuthority
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return JwtUser.builder()
                .userId(userId)
                .wxId(wxId)
                .password(password)
                .authorities(authorities)
                .build();
    }
}
