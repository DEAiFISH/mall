package com.deaifish.mall.pojo.po;

import com.alibaba.fastjson2.JSONObject;
import com.deaifish.mall.converter.StringJSONObjectConverter;
import com.deaifish.mall.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:44
 */

@Entity
@Table(name = "product", schema = "mall_db")
@Comment("商品表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
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
    @Column(name = "number", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '商品编码'")
    private String number;

    /**
     * 商品名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '商品名称'")
    private String name;

    /**
     * 商品分类ID
     */
    @Column(name = "classify_id", columnDefinition = "INT NOT NULL COMMENT '商品分类ID'")
    private Integer classifyId;

    /**
     * 商品品牌ID
     */
    @Column(name = "brand_id", columnDefinition = "INT NOT NULL COMMENT '商品品牌ID'")
    private Integer brandId;

    /**
     * 价格
     */
    @Column(name = "price", columnDefinition = "DOUBLE NOT NULL DEFAULT 0 COMMENT '价格'")
    private Double price;

    /**
     * 优惠价格
     */
    @Column(name = "preferential_price", columnDefinition = "DOUBLE COMMENT '优惠价格'")
    private Double preferentialPrice;

    /**
     * 特有规格参数
     */
    @Column(name = "parameter", columnDefinition = "VARCHAR(512) COMMENT '特有规格参数'")
    @Convert(converter = StringJSONObjectConverter.class)
    private JSONObject parameter;

    /**
     * 销量
     */
    @Column(name = "sale", columnDefinition = "INT NOT NULL DEFAULT 0 COMMENT '销量'")
    private Integer sale;

    /**
     * 简述
     */
    @Column(name = "brief_description", columnDefinition = "VARCHAR(512) NOT NULL COMMENT '简述'")
    private String briefDescription;

    /**
     * 详细描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(1024) COMMENT '详细描述'")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "TINYINT NOT NULL DEFAULT 1 COMMENT '状态'")
    private Byte status;

    /**
     * 封面图路径
     */
    @Column(name = "cover_picture", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '封面图路径'")
    private String coverPicture;

    /**
     * 详细图路径
     */
    @Column(name = "details_picture", columnDefinition = "VARCHAR(1024) NOT NULL COMMENT '详细图路径'")
    @Convert(converter = StringListConverter.class)
    private List<String> detailsPicture;
}
