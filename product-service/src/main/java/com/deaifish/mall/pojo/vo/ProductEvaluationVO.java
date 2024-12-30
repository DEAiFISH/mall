package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 15:03
 */
@Data
@Builder
public class ProductEvaluationVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品评价ID
     */
    @Schema(description = "商品评价ID")
    private Long evaluationId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 评价内容
     */
    @Schema(description = "评价内容")
    private String content;

    /**
     * 商家回复
     */
    @Schema(description = "商家回复")
    private String reply;

    /**
     * 是否回复
     */
    @Schema(description = "是否回复")
    private Boolean isReply;

    /**
     * 评分
     */
    @Schema(description = "评分")
    private Byte star;

    /**
     * 图片路径
     */
    @Schema(description = "图片路径")
    private List<String> picture;

    /**
     * 是否匿名
     */
    @Schema(description = "是否匿名")
    private Boolean isAnonymous;
}