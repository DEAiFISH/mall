package com.deaifish.mall.pojo.vo;

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
    private String Label;
    private Long value;
}