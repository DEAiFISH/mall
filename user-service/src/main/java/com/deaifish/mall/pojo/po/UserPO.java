package com.deaifish.mall.pojo.po;

import com.deaifish.mall.jpa.pojo.po.BasePO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 用户表
 *
 * @author DEAiFISH
 * @date 2024/12/5 16:26
 */

@Entity
@Table(schema = "mall_db", name = "user")
@Comment("用户表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class UserPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "COMMENT '用户ID'")
    private Long userId;

    /**
     * 微信ID
     */
    @Column(name = "wx_id", unique = true, length = 32, columnDefinition = "COMMENT '微信ID'")
    private String wxId;

    /**
     * 用户名
     */
    @Column(name = "nick_name", nullable = false, length = 32, columnDefinition = "COMMENT '用户名'")
    private String nickName;

    /**
     * 真实姓名
     */
    @Column(name = "real_name", length = 16, columnDefinition = "COMMENT '真实姓名'")
    private String realName;

    /**
     * 性别
     */
    @Column(name = "gender", columnDefinition = "DEFAULT 2 COMMENT '性别（男：1；女：0；未知：2）")
    private Byte gender;

    /**
     * 生日
     */
    @Column(name = "birthday", columnDefinition = "COMMENT '生日'")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 头像
     */
    @Column(name = "avatar", length = 256, columnDefinition = "default-user-photo.png' COMMENT '头像路径'")
    private String avatar;

    /**
     * 电话号
     */
    @Column(name = "phone", length = 16, columnDefinition = "COMMENT '电话号'")
    private String phone;

    /**
     * 个性签名
     */
    @Column(name = "message", length = 128, columnDefinition = "COMMENT '个性签名'")
    private String message;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, length = 128, columnDefinition = "COMMENT '密码'")
    private String password;

    /**
     * 支付密码
     */
    @Column(name = "payment_password", nullable = false, length = 128, columnDefinition = "COMMENT '支付密码'")
    private String paymentPassword;

    /**
     * 邮箱
     */
    @Column(name = "email", length = 32, columnDefinition = "COMMENT '邮箱'")
    private String email;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login", nullable = false, columnDefinition = "COMMENT '最后一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    /**
     * 状态（启用：1；禁用：0）
     */
    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 1 COMMENT '状态（启用：1；禁用：0）")
    private Byte status;

    /**
     * 用户积分
     */
    @Column(name = "integral", nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT '用户积分")
    private Integer integral;

    /**
     * 角色ID
     */
    @Column(name = "role_id", columnDefinition = "COMMENT '角色ID'")
    private Byte roleId;
}