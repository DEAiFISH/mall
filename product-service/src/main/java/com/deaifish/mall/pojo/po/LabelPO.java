package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:25
 */
@Entity
@Table(schema = "mall_db", name = "label")
@Comment("标签")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '名称'")
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
}
