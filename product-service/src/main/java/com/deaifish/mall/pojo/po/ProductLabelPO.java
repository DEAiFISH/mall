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
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:52
 */
@Entity
@Table(name = "product_label", schema = "mall_db")
@Comment("商品标签表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ProductLabelPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品标签表ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_label_id", columnDefinition = "BIGINT COMMENT '商品标签表ID'")
    private Long productLabelId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 标签ID
     */
    @Column(name = "label_id", columnDefinition = "INT NOT NULL COMMENT '标签ID'")
    private Integer labelId;
}