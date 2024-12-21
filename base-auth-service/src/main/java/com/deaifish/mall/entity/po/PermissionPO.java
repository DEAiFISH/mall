package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:05
 */
@Entity
@Table(schema = "mall_db", name = "permission")
@Comment("权限表")
@Getter
@Setter
public class PermissionPO {
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

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间")
    private LocalDateTime updateTime;
}
