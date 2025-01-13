package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

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
     * 创建者 ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '创建者 ID'")
    private Long userId;
}