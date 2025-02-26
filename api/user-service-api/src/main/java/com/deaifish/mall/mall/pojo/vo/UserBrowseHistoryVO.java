package com.deaifish.mall.mall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long historyId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片路径
     */
    private String picture;
}
