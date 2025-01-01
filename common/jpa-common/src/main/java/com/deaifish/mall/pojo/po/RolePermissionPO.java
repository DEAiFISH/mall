package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * @description 角色权限表
 *
 * @author DEAiFISH
 * @date 2024/12/10 10:31
 */
@Entity
@Table(schema = "mall_db", name = "role_permission")
@Comment("角色权限表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionPO extends BasePO {
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
}
