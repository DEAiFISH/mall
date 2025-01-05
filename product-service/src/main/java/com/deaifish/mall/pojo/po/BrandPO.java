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
 * @date 2024/12/12 14:47
 */
@Entity
@Table(name = "brand", schema = "mall_db")
@Comment("商品品牌表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class BrandPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品品牌ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", columnDefinition = "INT COMMENT '商品品牌ID'")
    private Integer brandId;

    /**
     * 品牌编码
     */
    @Column(name = "number", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '品牌编码'")
    private String number;

    /**
     * 品牌名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '品牌名称'")
    private String name;

    /**
     * 图标路径
     */
    @Column(name = "icon", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '图标路径'")
    private String icon;

    /**
     * 专区大图路径
     */
    @Column(name = "picture", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '专区大图路径'")
    private String picture;

    /**
     * 描述
     */
    @Column(name = "description", length = 512, columnDefinition = "VARCHAR(512) COMMENT '描述'")
    private String description;
}
