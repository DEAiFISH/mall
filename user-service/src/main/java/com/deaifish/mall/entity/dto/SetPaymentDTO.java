package com.deaifish.mall.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/18 11:25
 */
@Data
public class SetPaymentDTO {
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "支付密码")
    @NotBlank(message = "支付密码不能为空")
    private String paymentPassword;

    @Schema(description = "登录密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
