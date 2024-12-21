package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/10 10:31
 */
@Entity
@Table(schema = "mall_db", name = "role_permission")
@Comment("角色权限表")
public class RolePermissionPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_id", nullable = false, updatable = false, columnDefinition = "COMMENT '主键'")
    private Byte rolePermissionId;

    /**
     * 角色id
     */
    @Column(name = "role_id", columnDefinition = "COMMENT '角色id")
    private Byte roleId;

    /**
     * 权限id
     */
    @Column(name = "permission_id", columnDefinition = "COMMENT '权限id")
    private Byte permissionId;

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "COMMENT '用户id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间")
    private LocalDateTime updateTime;
}
