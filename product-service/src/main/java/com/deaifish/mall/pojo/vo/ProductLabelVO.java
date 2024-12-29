package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:52
 */
@Data
@Builder
public class ProductLabelVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品标签表ID
     */
    @Schema(description = "商品标签表ID")
    private Long productLabelId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 标签ID
     */
    @Schema(description = "标签ID")
    private Integer labelId;
}