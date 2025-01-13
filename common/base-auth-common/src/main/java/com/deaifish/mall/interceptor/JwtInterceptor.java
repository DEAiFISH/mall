package com.deaifish.mall.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.Claim;
import com.deaifish.mall.AuthUserContext;
import com.deaifish.mall.config.JWTProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.annotation.RequiresRole;
import com.deaifish.mall.pojo.bo.JwtUser;
import com.deaifish.mall.util.JWTUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;

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

        JwtUser userInfo =  jwtUtil.parseToken(userData);

        // 获取用户角色、权限信息
        List<String> authorities = userInfo.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // 获取 HandlerMethod（控制器方法）
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 获取方法上的 RequiresRole 注解
            RequiresRole requiresRole = handlerMethod.getMethodAnnotation(RequiresRole.class);
            Set<String> requiredRoles = new HashSet<>();
            if(requiresRole != null){
                requiredRoles.addAll(Set.of(requiresRole.value()));
            }
            // 获取类上的 RequiresRole 注解
            requiresRole = handlerMethod.getBeanType().getAnnotation(RequiresRole.class);
            if(requiresRole != null){
                requiredRoles.addAll(Set.of(requiresRole.value()));
            }

            if (!requiredRoles.isEmpty()) {
                // 检查用户是否具有至少一个所需的角色或权限
                boolean hasRequiredRole = false;
                for (String authority : authorities) {
                    if (requiredRoles.contains(authority)) {
                        hasRequiredRole = true;
                        break;
                    }
                }

                // 如果没有权限，则抛出异常
                if (!hasRequiredRole) {
                    throw new MallException("权限不足，无法访问该资源!");
                }
            }
        }

        // 将用户信息放入 ThreadLocal 中，以便后续的控制器方法使用
        AuthUserContext.set(userInfo);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理当前线程的用户信息
        AuthUserContext.clean();
    }
}