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
 * @date 2024/12/12 14:51
 */
@Entity
@Table(name = "stock", schema = "mall_db")
@Comment("库存表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockPO extends BasePO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", columnDefinition = "BIGINT COMMENT '库存ID'")
    private Long stockId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", nullable = false, unique = true, columnDefinition = "BIGINT COMMENT '商品ID'")
    private Long productId;

    /**
     * 库存量
     */
    @Column(name = "amount", nullable = false, columnDefinition = "INT COMMENT '库存量'")
    private Integer amount;

    /**
     * 警报库存量
     */
    @Column(name = "warning_amount", nullable = false, columnDefinition = "INT COMMENT '警报库存量'")
    private Integer warningAmount;
}
