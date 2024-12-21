package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 15:03
 */
@Entity
@Table(name = "product_evaluation", schema = "mall_db")
@Comment(value = "商品评价")
public class ProductEvaluationPO {

    /**
     * 商品评价ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id", nullable = false, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品评价ID'")
    private Long evaluationId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT NOT NULL COMMENT '用户ID'")
    private Long userId;

    /**
     * 评价内容
     */
    @Column(name = "content", nullable = false, length = 512, columnDefinition = "VARCHAR(512) NOT NULL COMMENT '内容'")
    private String content;

    /**
     * 商家回复
     */
    @Column(name = "reply", length = 512, columnDefinition = "VARCHAR(512) DEFAULT NULL COMMENT '商家回复'")
    private String reply;

    /**
     * 是否回复
     */
    @Column(name = "is_reply", nullable = false, columnDefinition = "BOOLEAN NOT NULL COMMENT '是否回复'")
    private Boolean isReply;

    /**
     * IP地址
     */
    @Column(name = "ip", nullable = false, length = 64, columnDefinition = "VARCHAR(64) NOT NULL COMMENT 'IP'")
    private String ip;

    /**
     * 评分
     */
    @Column(name = "start", nullable = false, columnDefinition = "TINYINT NOT NULL COMMENT '评分'")
    private Byte start;

    /**
     * 图片路径
     */
    @Column(name = "picture", length = 256, columnDefinition = "VARCHAR(256) DEFAULT NULL COMMENT '图片路径'")
    private String picture;

    /**
     * 是否匿名
     */
    @Column(name = "is_anonymous", nullable = false, columnDefinition = "BOOLEAN NOT NULL COMMENT '是否匿名'")
    private Boolean isAnonymous;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间'")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间'")
    private LocalDateTime updateTime;

    // Getters and Setters

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Boolean getIsReply() {
        return isReply;
    }

    public void setIsReply(Boolean isReply) {
        this.isReply = isReply;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Byte getStart() {
        return start;
    }

    public void setStart(Byte start) {
        this.start = start;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
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