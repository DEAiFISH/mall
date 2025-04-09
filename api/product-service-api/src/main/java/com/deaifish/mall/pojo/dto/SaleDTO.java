package com.deaifish.mall.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/04/09 16:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    private Long pid;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Integer num;
}
