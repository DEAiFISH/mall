package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/8 17:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductESVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 商品编码
     */
    @Schema(description = "商品编码")
    private String number;

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