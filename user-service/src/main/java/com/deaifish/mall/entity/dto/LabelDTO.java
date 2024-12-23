package com.deaifish.mall.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/23 11:23
 */
@Data
public class LabelDTO {
    /**
     * 标签ID
     */
    @Schema(description = "标签ID")
    private Integer labelId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 权重
     */
    @Schema(description = "权重")
    @NotNull(message = "权重不能为空")
    private Long weights;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
}
