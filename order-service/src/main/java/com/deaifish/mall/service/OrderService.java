package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.OrderDTO;
import com.deaifish.mall.pojo.vo.OrderVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/2 23:01
 */
public interface OrderService {
    /**
     * 根据订单ID查询
     * @param orderId
     * @return
     */
    OrderVO getByOrderId(Long orderId);

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    List<OrderVO> getByUserId(Long userId);

    /**
     * 根据商品ID查询
     * @param productId
     * @return
     */
    List<OrderVO> getByProductId(Long productId);

    /**
     * 查询订单是否超时
     * @param orderId
     * @return
     */
    boolean checkOrderIsTimeout(Long orderId);

    /**
     * 根据订单状态查询
     * @param orderDTO
     * @return
     */
    OrderVO update(OrderDTO orderDTO);

    /**
     * 更新订单状态
     * @param orderId
     * @param status
     * @return
     */
    OrderVO updateStatus(Long orderId, Byte status);

    /**
     * 更新订单地址
     * @param orderId
     * @param addressId
     * @return
     */
    OrderVO updateAddress(Long orderId, Long addressId);

    /**
     * 添加订单
     * @param orderDTO
     * @return
     */
    OrderVO add(OrderDTO orderDTO);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    void delete(Long orderId);
}
