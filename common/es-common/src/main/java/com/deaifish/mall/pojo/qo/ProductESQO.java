package com.deaifish.mall.pojo.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/8 17:30
 */
@Data
public class ProductESQO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 商品分类名称
     */
    @Schema(description = "商品分类名称")
    private String classifyName;

    /**
     * 商品品牌名称
     */
    @Schema(description = "商品品牌名称")
    private String brandName;

    /**
     * 价格底价
     */
    @Schema(description = "价格底价")
    private Double priceMin;
    /**
     * 价格高价
     */
    @Schema(description = "价格高价")
    private Double priceMax;

    /**
     * 简述
     */
    @Schema(description = "简述")
    private String briefDescription;
}