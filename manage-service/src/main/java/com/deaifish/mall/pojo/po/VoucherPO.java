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
 * @description 优惠卷表
 *
 * @date 2024/12/5 16:26
 */
@Entity
@Table(schema = "mall_db", name = "voucher")
@Comment("优惠卷表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class VoucherPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠卷ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id", columnDefinition = "COMMENT '优惠卷ID'")
    private Long voucherId;

    /**
     * 优惠卷名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "COMMENT '优惠卷名称'")
    private String name;

    /**
     * 优惠卷描述
     */
    @Column(name = "description", length = 256, columnDefinition = "COMMENT '优惠卷描述'")
    private String description;

    /**
     * 优惠金额
     */
    @Column(name = "price", nullable = false, columnDefinition = "COMMENT '优惠金额'")
    private Double price;

    /**
     * 余量
     */
    @Column(name = "amount", nullable = false, columnDefinition = "COMMENT '优惠卷余量'")
    private Integer amount;
}
