package com.deaifish.mall.pojo.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/21 11:02
 */
@Data
public class UserQO {
    /**
     * 微信ID
     */
    @Schema(description = "微信ID")
    private String wxId;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickName;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
}