package com.deaifish.mall.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.Claim;
import com.deaifish.mall.config.JWTProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.util.JWTUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @description Jwt 拦截器，用于验证 Token 的有效性。如果 Token 不合法或过期，则返回错误响应。
 *
 * @author DEAiFISH
 * @date 2025/1/1 13:51
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private JWTProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getServletPath();
        log.info("JwtInterceptor 拦截请求：{}", requestPath);

        String road = request.getHeader("ROAD");
        if(StrUtil.isNotBlank(road)
        && "FEIGN-CLIENT".equals(road)){
            return true;
        }

        // 动态排除路径
        if (jwtProperties.getExcludePath().contains(requestPath)) {
            return true;
        }

        // 获取 Header 中的 Token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            throw new MallException("未携带 Token，请重新登录!");
        }

        // 验证 Token
        Map<String, Claim> userData = jwtUtil.verifyToken(token);
        if (userData == null) {
            throw new MallException("登录失效，请重新登录!");
        }

        return true;
    }
}