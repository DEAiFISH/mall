package com.deaifish.mall.entity.po;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:45
 */
@Entity
@Table(name = "classify",schema = "mall_db")
public class ClassifyPO {

    /**
     * 商品分类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classify_id", columnDefinition = "INT COMMENT '商品分类ID'")
    private Integer classifyId;

    /**
     * 上一级分类ID
     */
    @Column(name = "parent_id", columnDefinition = "INT DEFAULT 0 COMMENT '上一级分类ID'")
    private Integer parentId;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '分类名称'")
    private String name;

    /**
     * 分类编码
     */
    @Column(name = "number", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '分类编码'")
    private String number;

    /**
     * 描述
     */
    @Column(name = "description", length = 512, columnDefinition = "VARCHAR(512) COMMENT '描述'")
    private String description;

    /**
     * 图标路径
     */
    @Column(name = "icon", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '图标路径'")
    private String icon;

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
    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
