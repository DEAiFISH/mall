package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 18:13
 */
@RestController
@RequestMapping("/order-service/v1")
@Tag(name = "订单内网接口")
@Validated
@RequiredArgsConstructor
public class OrderServiceApiController {
    private final OrderService orderService;

    /**
     * 完成订单
     * @param orderId
     * @return
     */
    @PutMapping("/finish/{id}")
    public R<OrderVO> finish(@PathVariable("id") @Parameter(description = "订单id") Long orderId) {
        return R.success("完成订单", orderService.finish(orderId));
    }

    /**
     * 支付订单
     * @param orderId
     * @param paymentMethod 支付方式（0-支付宝 1-微信）
     * @return
     */
    @PutMapping("/pay/{id}")
    public R<OrderVO> pay(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                          @RequestParam("paymentMethod") @Parameter(description = "支付方式") Byte paymentMethod) {
        return R.success("支付成功", orderService.pay(orderId, paymentMethod));
    }
}