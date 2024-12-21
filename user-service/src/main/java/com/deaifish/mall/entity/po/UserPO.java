package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/5 16:26
 */

@Entity
@Table(schema = "mall_db", name = "user")
@Comment("用户表")
public class UserPO {

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
    @Column(name = "wx_id", unique = true, length = 32 ,columnDefinition = "COMMENT '微信ID'")
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
    private LocalDateTime birthday;

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
    private LocalDateTime lastLogin;

    /**
     * 最后登录IP
     */
    @Column(name = "last_ip", nullable = false, length = 64, columnDefinition = "COMMENT '最后登录IP'")
    private String lastIp;

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

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间")
    private LocalDateTime updateTime;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Byte getRoleId() {
        return roleId;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}