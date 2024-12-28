package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description 标签VO
 *
 * @author DEAiFISH
 * @date 2024/12/23 11:23
 */
@Data
public class LabelVO {

    /**
     * 标签ID
     */
    @Schema(description = "标签ID")
    private Integer labelId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
}
