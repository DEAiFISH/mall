package com.deaifish.mall.service;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/2/25 16:09
 */
public interface PayService {
    /**
     * 支付方法
     *
     * @param orderId 订单id
     */
    void pay(Long orderId);

}