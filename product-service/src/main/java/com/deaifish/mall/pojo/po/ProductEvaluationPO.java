package com.deaifish.mall.pojo.po;

import com.deaifish.mall.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
public class ProductEvaluationPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品评价ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id", columnDefinition = "BIGINT COMMENT '商品评价ID'")
    private Long evaluationId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '用户ID'")
    private Long userId;

    /**
     * 评价内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(512) NOT NULL COMMENT '评价内容'")
    private String content;

    /**
     * 商家回复
     */
    @Column(name = "reply", columnDefinition = "VARCHAR(512) COMMENT '商家回复'")
    private String reply;

    /**
     * 是否回复
     */
    @Column(name = "is_reply", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否回复'")
    private Boolean isReply;

    /**
     * 评分
     */
    @Column(name = "star", columnDefinition = "TINYINT NOT NULL COMMENT '评分'")
    private Byte star;

    /**
     * 图片路径
     */
    @Column(name = "picture", columnDefinition = "VARCHAR(256) COMMENT '图片路径'")
    @Convert(converter = StringListConverter.class)
    private List<String> picture;

    /**
     * 是否匿名
     */
    @Column(name = "is_anonymous", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否匿名'")
    private Boolean isAnonymous;
}