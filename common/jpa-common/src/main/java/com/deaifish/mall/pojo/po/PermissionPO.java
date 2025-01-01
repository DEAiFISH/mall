package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

/**
 * @description 权限表
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:05
 */
@Entity
@Table(schema = "mall_db", name = "permission")
@Comment("权限表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionPO extends BasePO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", nullable = false, updatable = false, columnDefinition = "COMMENT '权限id'")
    private Byte permissionId;

    /**
     * 权限名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "COMMENT '权限名称")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", length = 128, columnDefinition = "COMMENT '描述'")
    private String description;

    /**
     * 创建者 ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "COMMENT '创建者ID'")
    private Long userId;
}
