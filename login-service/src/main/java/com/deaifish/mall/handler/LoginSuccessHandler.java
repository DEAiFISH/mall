package com.deaifish.mall.handler;

import com.deaifish.mall.pojo.bo.UserNamePasswordUserAuthToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 登录成功统一返回
 *
 * @author DEAiFISH
 * @date 2024/12/31 13:41
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserNamePasswordUserAuthToken userDetails = (UserNamePasswordUserAuthToken) authentication.getPrincipal();

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "成功");
        result.put("code", 200);
        result.put("token", userDetails.getToken());
        result.put("data", userDetails);
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().write(s);
    }
}
