package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:47
 */
@Data
@Builder
public class BrandVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品品牌ID
     */
    @Schema(description = "商品品牌ID")
    private Integer brandId;

    /**
     * 品牌编码
     */
    @Schema(description = "品牌编码")
    private String number;

    /**
     * 品牌名称
     */
    @Schema(description = "品牌名称")
    private String name;

    /**
     * 图标路径
     */
    @Schema(description = "图标路径")
    private String icon;

    /**
     * 专区大图路径
     */
    @Schema(description = "专区大图路径")
    private String picture;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
}
