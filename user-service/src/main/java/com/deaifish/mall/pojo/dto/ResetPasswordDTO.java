package com.deaifish.mall.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/20 14:07
 */
@Data
public class ResetPasswordDTO {
    @Schema(description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @Schema(description = "新登录密码")
    @NotBlank(message = "新登录密码不能为空")
    private String password;

    @Schema(description = "旧登录密码")
    @NotBlank(message = "旧登录密码不能为空")
    private String oldPassword;
}