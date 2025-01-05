package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @description 权限表
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
    @Schema(description = "权限ID")
    @NotNull(message = "权限ID不能为空", groups = UpdateGroup.class)
    private Byte permissionId;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    @NotBlank(message = "权限名称不能为空")
    @Length(max = 32, message = "权限名称长度不能超过{max}")
    private String name;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 128, message = "权限描述长度不能超过{max}")
    private String description;

    /**
     * 创建者 ID
     */
    @Schema(description = "创建者 ID")
    @NotBlank(message = "创建者ID不能为空")
    private Long userId;
}
