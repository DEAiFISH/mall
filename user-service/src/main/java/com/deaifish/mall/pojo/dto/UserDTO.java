package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 19:06
 */
@Data
public class UserDTO {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 微信ID
     */
    @Schema(description = "微信ID")
    @NotBlank(message = "微信ID不能为空", groups = {UpdateGroup.class})
    private String wxId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 31, message = "用户名长度不能超过{max}")
    private String nickName;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    @NotBlank(message = "真实姓名不能为空")
    @Length(max = 15, message = "真实姓名长度不能超过{max}")
    private String realName;

    /**
     * 性别
     */
    @Schema(description = "性别:默认未知")
    private Byte gender;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "生日不能为空")
    private Date birthday;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar = "default-user-photo.png";

    /**
     * 电话号
     */
    @Schema(description = "电话号")
    private String phone;

    /**
     * 个性签名
     */
    @Schema(description = "个性签名")
    @Length(max = 127, message = "个性签名长度不能超过{max}")
    private String message;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 支付密码
     */
    @Schema(description = "支付密码")
    @NotBlank(message = "支付密码不能为空")
    private String paymentPassword;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 状态（启用：1；禁用：0）
     */
    @Schema(description = "状态（启用：1；禁用：0）")
    private Byte status;

    /**
     * 用户积分
     */
    @Schema(description = "用户积分")
    private Integer integral;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Byte roleId;

    public void setStatus(Byte status) {
        this.status = status == null ? 1 : status;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral == null ? 0 : integral;
    }
}
