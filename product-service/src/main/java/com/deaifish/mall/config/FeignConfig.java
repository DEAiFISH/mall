package com.deaifish.mall.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 21:23
 */
@Configuration
@RequiredArgsConstructor
public class FeignConfig {
    private final HttpServletRequest httpServletRequest;

    @Bean
    public Retryer myRetryer() {
//        return Retryer.NEVER_RETRY; // 默认，不启动重试策略

        /*最大重试次数 3（1+2），重试间最大间隔时间 1s，初始间隔时间100ms */
        return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feiLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 获取当前请求中的 Authorization 请求头
                String authorizationHeader = httpServletRequest.getHeader("Authorization");

                // 如果存在 Authorization 头，动态添加到 Feign 请求头中
                if (authorizationHeader != null) {
                    requestTemplate.header("Authorization", authorizationHeader);
                }

                // 可以添加其他自定义的请求头，比如 "ROAD"
                requestTemplate.header("ROAD", "FEIGN-CLIENT");
            }
        };
    }
}
