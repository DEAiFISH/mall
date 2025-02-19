package com.deaifish.mall.filter;

import com.deaifish.mall.exception.MallException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/31 14:19
 */
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().startsWith("application/json")) {
            try {
                Map<String, String> requestBody = objectMapper.readValue(request.getInputStream(), Map.class);
                String username = requestBody.get(getUsernameParameter());
                String password = requestBody.get(getPasswordParameter());
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new MallException("Failed to parse authentication request body", e);
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
