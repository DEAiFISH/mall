package com.deaifish.mall.service;

import com.deaifish.mall.pojo.contanst.OrderCancelReason;
import com.deaifish.mall.pojo.dto.OrderDTO;
import com.deaifish.mall.pojo.qo.OrderQO;
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
     * 查询订单列表
     * @param qo
     * @return
     */
    List<OrderVO> list(OrderQO qo);

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
     * 支付订单
     * @param orderId
     * @param paymentMethod
     * @return
     */
    OrderVO pay(Long orderId, Byte paymentMethod);

    /**
     * 发货订单
     * @param orderId
     * @param courierNumber
     * @return
     */
    OrderVO send(Long orderId, String courierNumber);

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    OrderVO receive(Long orderId);

    /**
     * 完成订单
     * @param orderId
     * @return
     */
    OrderVO finish(Long orderId);

    /**
     * 取消订单
     * @param orderId
     * @param cancelReason
     * @return
     */
    OrderVO cancel(Long orderId, OrderCancelReason cancelReason);

    /**
     * 更新订单备注
     * @param orderId
     * @param memos
     * @return
     */
    OrderVO updateMemos(Long orderId, String memos);

    /**
     * 更新订单地址
     * @param orderId
     * @param addressId
     * @return
     */
    OrderVO updateAddress(Long orderId, Long addressId);

    /**
     * 恢复订单
     * @param orderId
     * @return
     */
    OrderVO recover(Long orderId);

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
