package com.deaifish.mall;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/2 23:00
 */
@RestController
@RequestMapping("/order/v1")
@Tag(name = "订单接口")
@Validated
public class OrderController {
    @Resource
    private OrderService orderService;

}
