package com.deaifish.mall.listener;

import com.deaifish.mall.config.OrderProperties;
import com.deaifish.mall.pojo.contanst.OrderCancelReason;
import com.deaifish.mall.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 18:53
 */
@Component
@Slf4j
public class OrderExpirationListener implements MessageListener {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderProperties orderProperties;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        log.info("监听到过期key：{}", key);

        if (!key.startsWith(orderProperties.getRedisOrderKey())) {
            return;
        }
        Long orderId = null;
        try {
            orderId = Long.valueOf(key.substring(orderProperties.getRedisOrderKey().length()));
        } catch (NumberFormatException e) {
            log.error("无法解析过期键的订单 ID，键值：{}", key, e);
            return;
        }

        try {
            orderService.cancel(orderId, OrderCancelReason.TIMEOUT.getCode());
            log.info("成功取消订单：{}", orderId);
        } catch (Exception e) {
            log.error("取消订单失败，订单 ID: {}", orderId, e);
        }
    }
}
