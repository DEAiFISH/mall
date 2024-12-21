package com.deaifish.mall.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/10 16:30
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced // 声明为负载均衡的RestTemplate
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
