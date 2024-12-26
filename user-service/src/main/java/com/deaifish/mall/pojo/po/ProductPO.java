package com.deaifish.mall.pojo.po;

import com.deaifish.mall.jpa.repository.converter.StringListConverter;
import com.deaifish.mall.jpa.repository.pojo.po.BasePO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:44
 */

@Entity
@Table(name = "product",schema = "mall_db")
@Comment("商品表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition = "BIGINT COMMENT '商品ID'")
    private Long productId;

    /**
     * 商品编码
     */
    @Column(name = "number", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '商品编码'")
    private String number;

    /**
     * 商品名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '商品名称'")
    private String name;

    /**
     * 商品分类ID
     */
    @Column(name = "classify_id", nullable = false, columnDefinition = "INT COMMENT '商品分类ID'")
    private Integer classifyId;

    /**
     * 商品品牌ID
     */
    @Column(name = "brand_id", nullable = false, columnDefinition = "INT COMMENT '商品品牌ID'")
    private Integer brandId;

    /**
     * 价格
     */
    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE COMMENT '价格'")
    private Double price;

    /**
     * 优惠价格
     */
    @Column(name = "preferential_price", columnDefinition = "DOUBLE COMMENT '优惠价格'")
    private Double preferentialPrice;

    /**
     * 特有规格参数（JSON）
     */
    @Column(name = "parameter", nullable = false, columnDefinition = "JSON COMMENT '特有规格参数（JSON格式：{属性：参数}）'")
    private String parameter;

    /**
     * 销量
     */
    @Column(name = "sale", nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT '销量'")
    private Integer sale;

    /**
     * 简述
     */
    @Column(name = "brief_description", nullable = false, length = 64, columnDefinition = "VARCHAR(64) COMMENT '简述'")
    private String briefDescription;

    /**
     * 详细描述
     */
    @Column(name = "description", length = 512, columnDefinition = "VARCHAR(512) COMMENT '详细描述'")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1 COMMENT '状态（1: 上架, 0: 下架）'")
    private Byte status;

    /**
     * 封面图路径
     */
    @Column(name = "cover_picture", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '封面图路径'")
    private String coverPicture;

    /**
     * 详细图路径
     */
    @Column(name = "details_picture", nullable = false, length = 1024, columnDefinition = "VARCHAR(256) COMMENT '详细图路径'")
    @Convert(converter = StringListConverter.class)
    private List<String> detailsPicture;
}
