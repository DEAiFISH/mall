package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @description 主页轮播图 DTO
 *
 * @date 2024/12/14 19:06
 */
@Data
public class HomepageCarouselDTO {

    /**
     * 轮播图ID
     */
    @Schema(description = "轮播图ID")
    @NotNull(message = "轮播图ID不能为空", groups = UpdateGroup.class)
    private Long chartId;

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
    @NotBlank(message = "商品名称不能为空")
    @Length(max = 127, message = "商品名称长度不能超过{max}")
    private String productName;

    /**
     * 图片路径
     */
    @Schema(description = "图片路径")
    @NotBlank(message = "图片路径不能为空")
    @Length(max = 255, message = "图片路径长度不能超过{max}")
    private String picture;

    /**
     * 状态：0-禁用，1-启用
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空", groups = UpdateGroup.class)
    private Byte status;
}
