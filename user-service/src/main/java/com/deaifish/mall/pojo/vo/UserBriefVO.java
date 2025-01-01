package com.deaifish.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 用户基本信息VO
 *
 * @author DEAiFISH
 * @date 2024/12/14 15:15
 */
@Builder
@Data
public class UserBriefVO implements Serializable {
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
     * 性别
     */
    @Schema(description = "性别")
    private Byte gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 最后一次登录时间
     */
    @Schema(description = "最后一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

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

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;
}
