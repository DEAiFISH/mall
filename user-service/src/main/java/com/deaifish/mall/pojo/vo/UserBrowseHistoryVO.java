package com.deaifish.mall.pojo.vo;

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
    @Schema(description = "用户浏览记录ID")
    private Long historyId;

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
    private String picture;
}
