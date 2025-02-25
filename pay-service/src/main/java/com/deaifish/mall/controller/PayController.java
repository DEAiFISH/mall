package com.deaifish.mall.controller;

import com.deaifish.mall.response.R;
import com.deaifish.mall.service.PayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/7 13:50
 */
@RestController
@RequestMapping("/pay/v1")
@Tag(name = "支付接口")
@Validated
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;

    /**
     * 支付
     *
     * @param orderId 订单id
     * @return
     */
    @PutMapping("/pay")
    public R<Boolean> pay(@RequestParam("orderId") Long orderId) {
        payService.pay(orderId);
        return R.success("支付成功",true);
    }
}