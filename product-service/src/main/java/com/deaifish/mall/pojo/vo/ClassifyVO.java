package com.deaifish.mall.pojo.vo;

import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:45
 */
@Data
@Builder
public class ClassifyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品分类ID
     */
    @Schema(description = "商品分类ID")
    private Integer classifyId;

    /**
     * 上一级分类ID
     */
    @Schema(description = "上一级分类ID")
    private Integer parentId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 分类编码
     */
    @Schema(description = "分类编码")
    private String number;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 图标路径
     */
    @Schema(description = "图标路径")
    private String icon;
}
