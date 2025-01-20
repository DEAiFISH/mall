package com.deaifish.mall.handler;

import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.util.JWTUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description 自定义登出成功处理器
 *
 * @author DEAiFISH
 * @date 2025/1/18 14:42
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private JWTUtil jwtUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(token)) {
            jwtUtil.deleteTokenFromRedis(token);
        }
    }
}