package com.deaifish.mall.config;

import com.deaifish.mall.pojo.po.OrderPO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 10:23
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, OrderPO> orderRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建 RedisTemplate 实例
        RedisTemplate<String, OrderPO> template = new RedisTemplate<>();

        // 设置连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        // 使用 StringRedisSerializer 来序列化和反序列化键
        template.setKeySerializer(new StringRedisSerializer());

        // 使用 GenericJackson2JsonRedisSerializer 来序列化和反序列化值
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 Hash 键和值
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;

    }
}