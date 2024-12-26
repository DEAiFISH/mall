package com.deaifish.mall.po;

import com.deaifish.mall.jpa.repository.pojo.po.BasePO;
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
 * @date 2024/12/12 14:52
 */
@Entity
@Table(name = "product_label",schema = "mall_db")
@Comment("商品标签表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductLabelPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品标签表ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_label_id", columnDefinition = "BIGINT COMMENT '商品标签表ID'")
    private Long productLabelId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT COMMENT '商品ID'")
    private Long productId;

    /**
     * 标签ID
     */
    @Column(name = "label_id", nullable = false, columnDefinition = "INT COMMENT '标签ID'")
    private Integer labelId;
}