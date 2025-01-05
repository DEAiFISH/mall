package com.deaifish.mall.pojo.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:00
 */
@Entity
@Table(schema = "mall_db", name = "role")
@Comment("角色")
@Getter
@Setter
public class RoleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false, columnDefinition = "COMMENT '角色ID'")
    private Byte roleId;

    /**
     * 角色名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "COMMENT '角色名称'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", length = 128, columnDefinition = "COMMENT '描述'")
    private String description;

    /**
     * 创建者 ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "COMMENT '创建者 ID'")
    private Long userId;
}