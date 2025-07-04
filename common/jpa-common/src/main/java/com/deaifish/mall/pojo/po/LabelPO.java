package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

/**
 * @description 标签
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:25
 */
@Entity
@Table(schema = "mall_db", name = "label", uniqueConstraints = {@UniqueConstraint(name = "idx_label_name", columnNames = {"name"})})
@Comment("标签")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class LabelPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id", columnDefinition = "INT COMMENT '标签ID'")
    private Integer labelId;

    /**
     * 名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '名称'")
    private String name;

    /**
     * 权重
     */
    @Column(name = "weights", columnDefinition = "BIGINT NOT NULL COMMENT '权重'")
    private Long weights;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(512) COMMENT '描述'")
    private String description;
}
