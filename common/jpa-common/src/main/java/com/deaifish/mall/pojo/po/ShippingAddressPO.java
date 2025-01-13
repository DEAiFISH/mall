package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

/**
 * @description 收货地址表
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:58
 */
@Entity
@Table(name = "shipping_address", schema = "mall_db")
@Comment("收货地址表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ShippingAddressPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '用户ID'")
    private Long userId;

    /**
     * 收货人名字
     */
    @Column(name = "name", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '收货人名字'")
    private String name;

    /**
     * 省
     */
    @Column(name = "province", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '省'")
    private String province;

    /**
     * 市
     */
    @Column(name = "city", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '市'")
    private String city;

    /**
     * 区/县
     */
    @Column(name = "area", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '区/县'")
    private String area;

    /**
     * 街道
     */
    @Column(name = "street", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '街道'")
    private String street;

    /**
     * 详细地址
     */
    @Column(name = "full", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '详细地址'")
    private String full;

    /**
     * 电话
     */
    @Column(name = "phone", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '电话'")
    private String phone;

    public String getAddress() {
        return name + " " + phone + " 送至 " + province + " " + city + " " + area + " " + street + " " + full;
    }
}
