package com.deaifish.mall.pojo.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

/**
 * @description 用户浏览历史
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:21
 */
@Entity
@Table(schema = "mall_db", name = "user_browse_history")
@Comment("用户浏览历史")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBrowseHistoryPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户浏览记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户浏览记录ID'")
    private Long historyId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户ID'")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT COMMENT '商品ID'")
    private Long productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name", nullable = false, length = 32, columnDefinition = "VARCHAR(256) COMMENT '商品名称'")
    private String productName;

    /**
     * 商品图片路径
     */
    @Column(name = "picture", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '商品图片路径'")
    private String picture;
}
