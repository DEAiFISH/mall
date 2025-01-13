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
@DynamicInsert
public class UserBrowseHistoryPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户浏览记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", columnDefinition = "BIGINT COMMENT '用户浏览记录ID'")
    private Long historyId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '用户ID'")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '商品名称'")
    private String productName;

    /**
     * 商品图片路径
     */
    @Column(name = "picture", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '商品图片路径'")
    private String picture;
}
