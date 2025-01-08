package com.deaifish.mall.controller;

import com.deaifish.mall.response.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/7 13:50
 */
@RestController
@RequestMapping("/pay/v1")
@Tag(name = "商品品牌管理")
@Validated
public class PayController {

    @GetMapping("/test")
    public R<Boolean> pay() {
        return R.success("支付成功",true);
    }
}