package com.deaifish.mall.pojo.vo;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description 商品详细信息VO类，用于前端展示。
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:55
 */
@Data
@Builder
public class ProductVO implements Serializable {
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
     * 特有规格参数（JSON）
     */
    @Schema(description = "特有规格参数")
    private JSONObject parameter;

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
     * 详细描述
     */
    @Schema(description = "详细描述")
    private String description;

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

    /**
     * 详细图路径
     */
    @Schema(description = "详细图路径")
    private List<String> detailsPicture;
}
