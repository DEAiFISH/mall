package com.deaifish.mall.pojo.po;

import com.deaifish.mall.jpa.pojo.po.BasePO;
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
 * @date 2024/12/12 14:45
 */
@Entity
@Table(name = "classify", schema = "mall_db")
@Comment("商品分类表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
