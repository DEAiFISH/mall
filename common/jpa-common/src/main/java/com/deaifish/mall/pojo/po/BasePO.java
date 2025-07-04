package com.deaifish.mall.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description 基础仓库类，用于存储创建时间和更新时间信息。此类为抽象类，旨在被继承使用。
 *
 * @author DEAiFISH
 * @date 2024/12/24 17:51
 */
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass // 表明这是一个映射实体类，但不直接映射到数据库表
public abstract class BasePO {

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    public BasePO() {
    }

    public BasePO(Date createTime, Date updateTime) {
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}