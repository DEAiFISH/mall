package com.deaifish.mall.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 14:17
 */
@Data
@Builder
public class ProductCollectDTO {

    /**
     * 商品收藏ID
     */
    @Schema(description = "商品收藏ID")
    private Long collectId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotNull(message = "商品名称不能为空")
    private String productName;

    /**
     * 商品图片路径
     */
    @Schema(description = "商品图片路径")
    @NotBlank(message = "商品图片路径不能为空")
    @Length(max = 255, message = "商品图片路径长度不能超过{max}")
    private String coverPicture;
}