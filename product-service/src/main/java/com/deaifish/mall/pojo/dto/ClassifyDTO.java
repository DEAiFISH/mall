package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class ClassifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品分类ID
     */
    @Schema(description = "商品分类ID")
    @NotNull(message = "商品分类ID不能为空", groups = UpdateGroup.class)
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
    @NotNull(message = "名称不能为空")
    @Length(max = 32, message = "名称长度不能超过{max}")
    private String name;

    /**
     * 分类编码
     */
    @Schema(description = "分类编码")
    @NotNull(message = "分类编码不能为空")
    @Length(max = 32, message = "分类编码长度不能超过{max}")
    private String number;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 512, message = "描述长度不能超过{max}")
    private String description;

    /**
     * 图标路径
     */
    @Schema(description = "图标路径")
    @Length(max = 256, message = "图标路径长度不能超过{max}")
    private String icon;
}
