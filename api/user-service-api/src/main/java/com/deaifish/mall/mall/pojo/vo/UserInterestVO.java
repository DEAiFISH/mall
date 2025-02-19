package com.deaifish.mall.mall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/2/19 17:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInterestVO {
    /**
     * 标签名
     */
    private String Label;

    /**
     * 兴趣度
     */
    private Long value;
}