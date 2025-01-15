package com.deaifish.mall.pojo.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 13:41
 */
@Data
public class RolePermissionQO {
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Byte roleId;

    /**
     * 权限id
     */
    @Schema(description = "权限id")
    private Byte permissionId;
}