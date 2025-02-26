package com.deaifish.mall.handler;

import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "退出成功");
        result.put("code", 200);
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().write(s);
    }
}