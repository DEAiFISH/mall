package com.deaifish.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 18:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "order")
public class OrderProperties {
    private String redisOrderKey = "ORDER:";
    private Long redisOrderTimeout = 10L;
}