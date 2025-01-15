package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}