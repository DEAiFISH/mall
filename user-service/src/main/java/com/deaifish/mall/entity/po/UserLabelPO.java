package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:27
 */
@Entity
@Table(schema = "mall_db", name = "user_label")
@Comment("用户标签表")
@Data
public class UserLabelPO {

    /**
     * 用户标签表ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_label_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户标签表ID'")
    private Long userLabelId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户ID'")
    private Long userId;

    /**
     * 标签ID
     */
    @Column(name = "label_id", nullable = false, columnDefinition = "INT COMMENT '标签ID'")
    private Integer labelId;

    /**
     * 兴趣度
     */
    @Column(name = "interest", nullable = false, columnDefinition = "INT COMMENT '兴趣度'")
    private Integer interest;

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