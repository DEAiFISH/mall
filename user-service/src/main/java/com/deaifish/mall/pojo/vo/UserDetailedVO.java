package com.deaifish.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description 用户详细信息视图对象
 *
 * @author DEAiFISH
 * @date 2024/12/14 15:15
 */
@Data
@Builder
public class UserDetailedVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 微信ID
     */
    @Schema(description = "微信ID")
    private String wxId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String nickName;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Byte gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 电话号
     */
    @Schema(description = "电话号")
    private String phone;

    /**
     * 个性签名
     */
    @Schema(description = "个性签名")
    private String message;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 最后一次登录时间
     */
    @Schema(description = "最后一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;

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
}
