package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 14:16
 */
@Data
@Builder
public class ProductCollectVO {

    /**
     * 商品收藏ID
     */
    @Schema(description = "商品收藏ID")
    private Long collectId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

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
     * 商品图片路径
     */
    @Schema(description = "商品图片路径")
    private String coverPicture;
}