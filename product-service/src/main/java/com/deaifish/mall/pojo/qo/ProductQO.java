package com.deaifish.mall.pojo.qo;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class ProductQO implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 原价格上限
     */
    @Schema(description = "原价格上限")
    private Double priceUP;

    /**
     * 原价格下限
     */
    @Schema(description = "原价格下限")
    private Double priceDown;

    /**
     * 优惠价格上限
     */
    @Schema(description = "优惠价格上限")
    private Double preferentialPriceUP;

    /**
     * 优惠价格下限
     */
    @Schema(description = "优惠价格下限")
    private Double preferentialPriceDown;

    /**
     * 销量上限
     */
    @Schema(description = "销量上限")
    private Integer saleUP;

    /**
     * 销量下限
     */
    @Schema(description = "销量下限")
    private Integer saleDown;
}
