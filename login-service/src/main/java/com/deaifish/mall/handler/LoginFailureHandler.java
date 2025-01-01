package com.deaifish.mall.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 登录失败统一返回
 *
 * @author DEAiFISH
 * @date 2024/12/31 13:46
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", exception.getMessage());
        result.put("code", 400);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("refresh", "1;/login?error=true");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);
    }
}
