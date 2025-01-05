package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @description 搜索关键词 DTO
 *
 * @date 2024/12/14 19:06
 */
@Data
public class SearchKeywordDTO {

    /**
     * 搜索关键词ID
     */
    @Schema(description = "搜索关键词ID")
    @NotNull(message = "搜索关键词ID不能为空", groups = UpdateGroup.class)
    private Long searchKeywordId;

    /**
     * 关键词内容
     */
    @Schema(description = "关键词内容")
    @NotBlank(message = "关键词内容不能为空")
    @Length(max = 31, message = "关键词内容长度不能超过{max}")
    private String content;

    /**
     * 搜索次数
     */
    @Schema(description = "搜索次数")
    @NotNull(message = "搜索次数不能为空")
    private Long amount;
}
