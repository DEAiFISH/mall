package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.jpa.pojo.po.BasePO;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 15:03
 */
@Data
@Builder
public class ProductEvaluationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品评价ID
     */
    @Schema(description = "商品评价ID")
    @NotNull(message = "商品评价ID不能为空", groups = {UpdateGroup.class})
    private Long evaluationId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 评价内容
     */
    @Schema(description = "评价内容")
    @NotBlank(message = "评价内容不能为空")
    @Length(max = 512, message = "评价内容长度不能超过{max}")
    private String content;

    /**
     * 商家回复
     */
    @Schema(description = "商家回复")
    @NotBlank(message = "商家回复不能为空")
    @Length(max = 512, message = "商家回复长度不能超过{max}")
    private String reply;

    /**
     * 是否回复
     */
    @Schema(description = "是否回复")
    @NotNull(message = "是否回复不能为空")
    private Boolean isReply;

    /**
     * 评分
     */
    @Schema(description = "评分")
    @NotNull(message = "评分不能为空")
    private Byte star;

    /**
     * 图片路径
     */
    @Schema(description = "图片路径")
    @NotBlank(message = "图片路径不能为空")
    @Length(max = 256, message = "图片路径长度不能超过{max}")
    private String picture;

    /**
     * 是否匿名
     */
    @Schema(description = "是否匿名")
    @NotNull(message = "是否匿名不能为空")
    private Boolean isAnonymous;
}