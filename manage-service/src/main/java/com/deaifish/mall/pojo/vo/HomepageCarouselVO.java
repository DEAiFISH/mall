package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description 主页轮播图
 *
 * @date 2024/12/14 19:06
 */
@Data
public class HomepageCarouselVO {

    /**
     * 轮播图ID
     */
    @Schema(description = "轮播图ID")
    private Long chartId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;

    /**
     * 图片路径
     */
    @Schema(description = "图片路径")
    private String picture;

    @Schema(description = "状态")
    private Byte status;
}
