package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 角色权限表
 *
 * @author DEAiFISH
 * @date 2024/12/10 10:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDTO {

    @Schema(description = "角色权限表id")
    @NotNull(message = "角色id不能为空", groups = UpdateGroup.class)
    private Byte rolePermissionId;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Byte roleId;

    /**
     * 权限id
     */
    @Schema(description = "权限id")
    @NotNull(message = "权限id不能为空")
    private Byte permissionId;

    /**
     * 创建者id
     */
    @Schema(description = "创建者id")
    @NotNull(message = "创建者id不能为空")
    private Long userId;
}
