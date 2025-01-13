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
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:45
 */
@Entity
@Table(name = "classify", schema = "mall_db")
@Comment("商品分类表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ClassifyPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @Column(name = "parent_id", columnDefinition = "INT COMMENT '上一级分类ID'")
    private Integer parentId;

    /**
     * 名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '名称'")
    private String name;

    /**
     * 分类编码
     */
    @Column(name = "number", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '分类编码'")
    private String number;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(512) COMMENT '描述'")
    private String description;

    /**
     * 图标路径
     */
    @Column(name = "icon", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '图标路径'")
    private String icon;
}
