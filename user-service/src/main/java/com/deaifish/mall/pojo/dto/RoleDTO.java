package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    /**
     * 角色 ID
     */
    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为空", groups = UpdateGroup.class)
    private Byte roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @NotNull(message = "角色名称不能为空")
    private String name;

    /**
     * 角色状态
     */
    @Schema(description = "角色状态")
    @NotNull(message = "角色状态不能为空", groups = UpdateGroup.class)
    private Byte status;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @NotNull(message = "描述不能为空")
    private String description;
}