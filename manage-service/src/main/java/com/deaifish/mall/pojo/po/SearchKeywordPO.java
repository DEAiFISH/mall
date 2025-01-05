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
 * @description 搜索关键词表
 *
 * @date 2024/12/5 16:26
 */
@Entity
@Table(schema = "mall_db", name = "search_keyword")
@Comment("搜索关键词表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class SearchKeywordPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索关键词ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_keyword_id", columnDefinition = "COMMENT '搜索关键词ID'")
    private Long searchKeywordId;

    /**
     * 关键词内容
     */
    @Column(name = "content", nullable = false, length = 32, columnDefinition = "COMMENT '关键词内容'")
    private String content;

    /**
     * 搜索次数
     */
    @Column(name = "amount", nullable = false, columnDefinition = "COMMENT '搜索次数'")
    private Long amount;
}
