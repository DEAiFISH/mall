package com.deaifish.mall.handler;

import com.deaifish.mall.pojo.bo.UserNamePasswordUserAuthToken;
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
@Resource
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private JWTUtil jwtUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserNamePasswordUserAuthToken userDetails = (UserNamePasswordUserAuthToken) authentication.getPrincipal();
        jwtUtil.deleteJwtFromRedis(userDetails.getUsername());

        response.sendRedirect("/login");
    }
}