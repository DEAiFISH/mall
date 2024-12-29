package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


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
    @NotNull(message = "标签ID不能为空", groups = {UpdateGroup.class})
    private Integer labelId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 31, message = "标签名称长度不能超过{max}")
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
    @Length(max = 511, message = "标签描述长度不能超过{max}")
    private String description;
}
