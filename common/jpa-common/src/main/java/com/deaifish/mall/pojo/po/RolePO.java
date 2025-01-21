package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

/**
 * @description 角色表
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:00
 */
@Entity
@Table(schema = "mall_db", name = "role")
@Comment("角色")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class RolePO extends BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", columnDefinition = "TINYINT COMMENT '角色 ID'")
    private Byte roleId;

    /**
     * 角色名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '角色名称'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(128) COMMENT '描述'")
    private String description;

    /**
     * 角色状态
     */
    @Column(name = "status", columnDefinition = "TINYINT NOT NULL DEFAULT 1 COMMENT '角色状态'")
    private Byte status;

    /**
     * 创建者 ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '创建者 ID'")
    private Long userId;
}