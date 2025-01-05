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
 * @description 主页轮播图表
 *
 * @date 2024/12/5 16:26
 */
@Entity
@Table(schema = "mall_db", name = "homepage_carousel")
@Comment("主页轮播图表")
@Data
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
    @Column(name = "chart_id", columnDefinition = "COMMENT '轮播图ID'")
    private Long chartId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", nullable = false, columnDefinition = "COMMENT '商品ID'")
    private Long productId;

    /**
     * 图片路径
     */
    @Column(name = "picture", nullable = false, length = 256, columnDefinition = "COMMENT '图片路径'")
    private String picture;
}
