package com.deaifish.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long userId;

    /**
     * 微信ID
     */
    private String wxId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话号
     */
    private String phone;

    /**
     * 个性签名
     */
    private String message;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;

    /**
     * 状态（启用：1；禁用：0）
     */
    private Byte status;

    /**
     * 用户积分
     */
    private Integer integral;

    /**
     * 角色ID
     */
    private Byte roleId;
}
