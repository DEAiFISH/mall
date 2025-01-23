package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

/**
 * @author DEAiFISH
 * @description 主页轮播图表
 *
 * @date 2024/12/5 16:26
 */
@Entity
@Table(schema = "mall_db", name = "homepage_carousel")
@Comment("主页轮播图表")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class HomepageCarouselPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chart_id", columnDefinition = "BIGINT COMMENT '轮播图ID'")
    private Long chartId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '商品名称'")
    private String productName;

    /**
     * 图片路径
     */
    @Column(name = "picture", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '图片路径'")
    private String picture;

    /**
     * 轮播图状态，0-禁用，1-开启
     */
    @Column(name = "status", columnDefinition = "TINYINT NOT NULL DEFAULT '1' COMMENT '轮播图状态，0-禁用，1-开启'")
    private Byte status;
}
