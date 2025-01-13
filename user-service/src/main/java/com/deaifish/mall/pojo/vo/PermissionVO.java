package com.deaifish.mall.pojo.vo;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVO {
    @Schema(description = "权限ID")
    private Byte permissionId;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String name;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 创建者 ID
     */
    @Schema(description = "创建者ID")
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