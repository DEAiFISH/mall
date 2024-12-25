package com.deaifish.mall.entity.vo;

import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @description 用户浏览历史
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBrowseHistoryVO {

    /**
     * 用户浏览记录ID
     */
    @NotNull(message = "用户浏览记录ID不能为null" ,groups = UpdateGroup.class)
    @Schema(description = "用户浏览记录ID")
    private Long historyId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为null")
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为null")
    @Schema(description = "商品ID")
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
    private String picture;
}
