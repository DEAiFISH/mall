package com.deaifish.mall.pojo.po;

import com.deaifish.mall.jpa.pojo.po.BasePO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

/**
 * @description 收货地址表
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:58
 */
@Entity
@Table(name = "shipping_address",schema = "mall_db")
@Comment("收货地址表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
