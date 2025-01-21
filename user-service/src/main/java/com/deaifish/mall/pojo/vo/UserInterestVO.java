package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 18:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInterestVO {
    /**
     * 标签名
     */
    @Schema(description = "标签名")
    private String Label;

    /**
     * 兴趣度
     */
    @Schema(description = "兴趣度")
    private Long value;
}