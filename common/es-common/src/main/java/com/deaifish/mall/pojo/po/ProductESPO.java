package com.deaifish.mall.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.IdType;

import java.io.Serializable;

/**
 * @description 商品ES实体类
 *
 * @author DEAiFISH
 * @date 2025/1/7 14:10
 */
@Data
@IndexName(value = "product_es")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductESPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @IndexId(type = IdType.CUSTOMIZE)
    @IndexField(value = "product_id")
    private Long productId;

    /**
     * 商品编码
     */
    @IndexField(value = "number")
    private String number;

    /**
     * 商品名称
     */
    @IndexField(value = "product_name", fieldType = FieldType.TEXT, analyzer = "ik_max_word")
    private String name;

    /**
     * 商品分类ID
     */
    @IndexField(value = "classify_id", fieldType = FieldType.KEYWORD, analyzer = "ik_max_word")
    private Long classifyId;

    /**
     * 商品分类名称
     */
    @IndexField(value = "classify_name", fieldType = FieldType.KEYWORD, ignoreCase = true , searchAnalyzer = "ik_max_word")
    private String classifyName;

    /**
     * 商品品牌ID
     */
    @IndexField(value = "brand_id", fieldType = FieldType.KEYWORD, analyzer = "ik_max_word")
    private Long brandId;

    /**
     * 商品品牌名称
     */
    @IndexField(value = "brand_name", fieldType = FieldType.KEYWORD, ignoreCase = true , searchAnalyzer = "ik_max_word")
    private String brandName;

    /**
     * 价格
     */
    @IndexField(value = "price")
    private Double price;

    /**
     * 优惠价格
     */
    @IndexField(value = "preferential_price")
    private Double preferentialPrice;

    /**
     * 库存量
     */
    @IndexField(value = "stock")
    private Integer stock;

    /**
     * 简述
     */
    @IndexField(value = "brief_description")
    private String briefDescription;

    /**
     * 状态
     */
    @IndexField(value = "status")
    private Byte status;

    /**
     * 封面图路径
     */
    @IndexField(value = "cover_picture")
    private String coverPicture;
}