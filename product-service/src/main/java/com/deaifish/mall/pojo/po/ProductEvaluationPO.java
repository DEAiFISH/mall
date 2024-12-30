package com.deaifish.mall.pojo.po;

import com.deaifish.mall.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 15:03
 */
@Entity
@Table(name = "product_evaluation", schema = "mall_db")
@Comment(value = "商品评价")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluationPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 评分
     */
    @Column(name = "star", nullable = false, columnDefinition = "TINYINT NOT NULL COMMENT '评分'")
    private Byte star;

    /**
     * 图片路径
     */
    @Column(name = "picture", length = 256, columnDefinition = "VARCHAR(256) DEFAULT NULL COMMENT '图片路径'")
    @Convert(converter = StringListConverter.class)
    private List<String> picture;

    /**
     * 是否匿名
     */
    @Column(name = "is_anonymous", nullable = false, columnDefinition = "BOOLEAN NOT NULL COMMENT '是否匿名'")
    private Boolean isAnonymous;
}