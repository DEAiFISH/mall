package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class RoleVO {

    /**
     * 角色 ID
     */
    @Schema(description = "角色ID")
    private Byte roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色状态
     */
    @Schema(description = "角色状态")
    private Byte status;

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