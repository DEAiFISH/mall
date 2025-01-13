package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
public class PermissionPO extends BasePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", columnDefinition = "TINYINT COMMENT '权限 ID'")
    private Byte permissionId;

    /**
     * 权限名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '权限名称'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(128) COMMENT '描述'")
    private String description;

    /**
     * 创建者 ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '创建者 ID'")
    private Long userId;
}
