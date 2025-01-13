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
 * @description 商品收藏表
 *
 * @author DEAiFISH
 * @date 2024/12/12 15:14
 */
@Entity
@Table(name = "product_collect", schema = "mall_db")
@Comment("商品收藏表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ProductCollectPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品收藏ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collect_id", columnDefinition = "BIGINT COMMENT '商品收藏ID'")
    private Long collectId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '用户ID'")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '商品名称'")
    private String productName;

    /**
     * 商品图片路径
     */
    @Column(name = "picture", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '商品图片路径'")
    private String picture;
}
