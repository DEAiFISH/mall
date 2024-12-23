package com.deaifish.mall.entity.vo;

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
    private Integer labelId;

    /**
     * 名称
     */
    private String name;

    /**
     * 权重
     */
    private Long weights;

    /**
     * 描述
     */
    private String description;
}
