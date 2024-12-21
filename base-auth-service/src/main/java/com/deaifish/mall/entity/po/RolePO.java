package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
public class RolePO {

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

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private LocalDateTime updateTime;
}