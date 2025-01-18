package com.deaifish.mall.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/18 11:31
 */
@Data
public class SetPasswordDTO {
    @Schema(description = "微信id")
    @NotNull(message = "微信id不能为空")
    private String wxId;

    @Schema(description = "登录密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
