package com.deaifish.mall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/7 20:16
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * @description 配置拦截器链
     *
     * @author DEAiFISH
     * @date 2023/12/22 00:07
     * @param http
     * @return org.springframework.security.web.SecurityFilterChain
     */
//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
//                    requests.requestMatchers("/vc.jpg", "/index","/login.html","404.html").permitAll()  // 放行资源必须放在拦截之前
//                            .anyRequest().authenticated();  // 拦截资源
                    requests.anyRequest().permitAll();
                })

                .formLogin((form) -> form.loginPage("/login.html")       // 用来指定默认登录页面 注意：一旦自定义登录页面以后必须指定登录url
                        .loginProcessingUrl("/doLogin") // 指定处理登录请求 url

                        .usernameParameter("uname")  // 指定用户名匹配
                        .passwordParameter("passwd")  // 指定密码匹配

//                        .successForwardUrl("/index")    // 认证成功 forward 跳转路径 始终在认证成功后跳转到指定的请求
                        //  认证成功 redirect 之后跳转 根据上一次保存请求进行成功跳转
                        //.defaultSuccessUrl("/index",true /* 就跟successForwardUrl一样了， 默认为false */)

                        .successHandler((request, response, authentication) -> {
                            Map<String, Object> result = new HashMap<>();
                            result.put("msg", "成功");
                            result.put("status", 200);
                            response.setContentType("application/json;charset=UTF-8");
                            String s = new ObjectMapper().writeValueAsString(result);
                            response.getWriter().write(s);
                        })  // 自定义认证成功处理 前后端分离的处理方案

                        //.failureForwardUrl("/login.html")   // 认证失败之后 forward 跳转
                        //.failureUrl("/login.html")   // 默认 认证失败之后 redirect 跳转

                        .failureHandler((request, response, exception) -> {
                            Map<String, Object> result = new HashMap<>();
                            result.put("msg", exception.getMessage());
                            result.put("status", 400);
                            response.setContentType("application/json;charset=UTF-8");
                            response.setHeader("refresh", "1;/login.html");
                            String s = new ObjectMapper().writeValueAsString(result);
                            response.getWriter().println(s);
                        })  // 自定义认证失败处理 前后端分离的处理方案

                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")   // 指定注销登录 默认请求方式必须是Get
                        .logoutRequestMatcher(
                                new OrRequestMatcher(
                                        new AntPathRequestMatcher("/aa", "GET"),
                                        new AntPathRequestMatcher("/bb", "POST")
                                )
                        )
                        .invalidateHttpSession(true)    // 默认 会话失效
                        .clearAuthentication(true)  // 默认 清楚认证标记
                        //.logoutSuccessUrl("/login.html")    // 注销登录 成功之后跳转页面

                        .logoutSuccessHandler(  // 注销成功之后的处理
                                (request, response, authentication) -> {
                                    Map<String, Object> result = new HashMap<>();
                                    result.put("msg", "注销成功，当前认证对象为：" + authentication);
                                    result.put("status", 400);
                                    response.setContentType("application/json;charset=UTF-8");
                                    response.setHeader("refresh", "5;/login.html");
                                    String s = new ObjectMapper().writeValueAsString(result);
                                    response.getWriter().println(s);
                                }
                        )
                        .permitAll())

                // 验证码
//                .addFilterBefore(kaptchaFilter(), UsernamePasswordAuthenticationFilter.class)

                .csrf(AbstractHttpConfigurer::disable)   // 关闭 csrf 跨站请求保护、

                .rememberMe(rememberMe -> {   // 记住我功能
                    rememberMe
                            //.alwaysRemember(true)   // 始终开启记住我
                            .rememberMeParameter("rememberMe")   // 设置开启rememberMe的参数
                            .key(String.valueOf(UUID.randomUUID())) // 设置加密的盐
//                            .rememberMeServices(rememberMeServices())
                    ;
                })

                .sessionManagement(sessionManagement -> {
                    sessionManagement.maximumSessions(1)
                    ;
                })
        ;

        return http.build();
    }
}
