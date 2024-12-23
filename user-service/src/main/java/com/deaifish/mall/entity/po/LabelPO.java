package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:25
 */
@Entity
@Table(schema = "mall_db", name = "label")
@Comment("标签")
public class LabelPO {

    /**
     * 标签ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id", nullable = false, columnDefinition = "INT COMMENT '标签ID'")
    private Integer labelId;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 32, unique = true , columnDefinition = "VARCHAR(32) COMMENT '名称'")
    private String name;

    /**
     * 权重
     */
    @Column(name = "weights", nullable = false, columnDefinition = "BIGINT COMMENT '权重'")
    private Long weights;

    /**
     * 描述
     */
    @Column(name = "description", length = 512, columnDefinition = "VARCHAR(512) COMMENT '描述'")
    private String description;

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

    // Getters and Setters
    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeights() {
        return weights;
    }

    public void setWeights(Long weights) {
        this.weights = weights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
