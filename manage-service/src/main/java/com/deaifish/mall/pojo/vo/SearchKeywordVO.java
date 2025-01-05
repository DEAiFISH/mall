package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description 搜索关键词
 *
 * @date 2024/12/14 19:06
 */
@Data
public class SearchKeywordVO {

    /**
     * 搜索关键词ID
     */
    @Schema(description = "搜索关键词ID")
    private Long searchKeywordId;

    /**
     * 关键词内容
     */
    @Schema(description = "关键词内容")
    private String content;

    /**
     * 搜索次数
     */
    @Schema(description = "搜索次数")
    private Long amount;
}
