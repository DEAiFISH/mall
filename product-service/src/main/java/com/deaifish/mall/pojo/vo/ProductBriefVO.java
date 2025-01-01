package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 商品简要信息VO类，用于前端展示。
 *
 * @author DEAiFISH
 * @date 2024/12/28 15:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBriefVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 商品分类ID
     */
    @Schema(description = "商品分类ID")
    private Integer classifyId;

    /**
     * 商品分类名称
     */
    @Schema(description = "商品分类名称")
    private String classifyName;

    /**
     * 商品品牌ID
     */
    @Schema(description = "商品品牌ID")
    private Integer brandId;

    /**
     * 商品品牌名称
     */
    @Schema(description = "商品品牌名称")
    private String brandName;

    /**
     * 价格
     */
    @Schema(description = "价格")
    private Double price;

    /**
     * 优惠价格
     */
    @Schema(description = "优惠价格")
    private Double preferentialPrice;

    /**
     * 销量
     */
    @Schema(description = "销量")
    private Integer sale;

    /**
     * 库存量
     */
    @Schema(description = "库存量")
    private Integer stock;

    /**
     * 简述
     */
    @Schema(description = "简述")
    private String briefDescription;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Byte status;

    /**
     * 封面图路径
     */
    @Schema(description = "封面图路径")
    private String coverPicture;
}
