package com.deaifish.mall.api;

import com.deaifish.mall.fallback.OrderServiceApiFallBack;
import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 18:16
 */
@FeignClient(value = "order-service", path = "/order-service/v1", fallback = OrderServiceApiFallBack.class)
public interface OrderServiceApi {
    /**
     * 完成订单
     * @param orderId
     * @return
     */
    @PutMapping("/finish/{id}")
    public R<OrderVO> finish(@PathVariable("id") Long orderId) ;

    /**
     * 支付订单
     * @param orderId 订单ID
     * @param paymentMethod 支付方式（0-支付宝 1-微信）
     * @return
     */
    @PutMapping("/pay/{id}")
    public R<OrderVO> pay(@PathVariable("id") Long orderId,
                          @RequestParam("paymentMethod")  Byte paymentMethod);
}