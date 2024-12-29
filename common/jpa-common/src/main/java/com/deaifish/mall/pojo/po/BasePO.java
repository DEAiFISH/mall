package com.deaifish.mall.pojo.po;

import jakarta.persistence.Column;

import java.util.Date;

/**
 * @description 基础仓库类，用于存储创建时间和更新时间信息。此类为抽象类，旨在被继承使用。
 *
 * @author DEAiFISH
 * @date 2024/12/24 17:51
 */
public abstract class BasePO {

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    protected Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    protected Date updateTime;

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