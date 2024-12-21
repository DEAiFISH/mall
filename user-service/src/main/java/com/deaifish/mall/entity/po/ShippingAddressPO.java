package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:58
 */
@Entity
@Table(name = "shipping_address",schema = "mall_db")
@Comment("收货地址表")
public class ShippingAddressPO {

    /**
     * 收货地址ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", columnDefinition = "BIGINT COMMENT '收货地址ID'")
    private Long addressId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户ID'")
    private Long userId;

    /**
     * 收货人名字
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '收货人名字'")
    private String name;

    /**
     * 省
     */
    @Column(name = "province", nullable = false, length = 16, columnDefinition = "VARCHAR(16) COMMENT '省'")
    private String province;

    /**
     * 市
     */
    @Column(name = "city", nullable = false, length = 16, columnDefinition = "VARCHAR(16) COMMENT '市'")
    private String city;

    /**
     * 区/县
     */
    @Column(name = "area", nullable = false, length = 16, columnDefinition = "VARCHAR(16) COMMENT '区/县'")
    private String area;

    /**
     * 街道
     */
    @Column(name = "street", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '街道'")
    private String street;

    /**
     * 详细地址
     */
    @Column(name = "full", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '详细地址'")
    private String full;

    /**
     * 电话
     */
    @Column(name = "phone", nullable = false, length = 16, columnDefinition = "VARCHAR(16) COMMENT '电话'")
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private LocalDateTime updateTime;

    // Getters and Setters
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
