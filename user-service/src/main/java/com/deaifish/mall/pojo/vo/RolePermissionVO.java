package com.deaifish.mall.pojo.vo;

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
public class RolePermissionVO {

    @Schema(description = "角色权限表id")
    private Byte rolePermissionId;

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

    /**
     * 创建者id
     */
    @Schema(description = "创建者id")
    private Long userId;

    /**
     * 创建者用户名
     */
    @Schema(description = "创建者用户名")
    private String nickName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private String createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private String updateTime;
}
