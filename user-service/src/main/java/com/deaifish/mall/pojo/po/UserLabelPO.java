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
 * @description 用户标签表
 *
 * @author DEAiFISH
 * @date 2024/12/12 12:27
 */
@Entity
@Table(schema = "mall_db", name = "user_label")
@Comment("用户标签表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLabelPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标签表ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_label_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户标签表ID'")
    private Long userLabelId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '用户ID'")
    private Long userId;

    /**
     * 标签ID
     */
    @Column(name = "label_id", nullable = false, columnDefinition = "INT COMMENT '标签ID'")
    private Integer labelId;

    /**
     * 兴趣度
     */
    @Column(name = "interest", nullable = false, columnDefinition = "BIGINT COMMENT '兴趣度'")
    private Long interest;

    public void addInterest(Long weight) {
        this.interest += weight;
    }
}