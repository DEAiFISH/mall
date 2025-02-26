package com.deaifish.mall.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
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
public class UserBrowseHistoryDTO {

    /**
     * 用户浏览记录ID
     */
    @NotNull(message = "用户浏览记录ID不能为null", groups = UpdateGroup.class)
    private Long historyId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为null")
    private Long userId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为null")
    private Long productId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    private String productName;

    /**
     * 商品图片路径
     */
    @NotBlank(message = "商品图片路径不能为空")
    @Length(max = 255, message = "商品图片路径长度不能超过{max}")
    private String picture;
}
