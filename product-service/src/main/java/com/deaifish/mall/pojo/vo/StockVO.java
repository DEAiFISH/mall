package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:51
 */
@Data
@Builder
public class StockVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @Schema(description = "库存ID")
    private Long stockId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    private Long productId;

    /**
     * 库存量
     */
    @Schema(description = "库存量")
    private Integer amount;

    /**
     * 警报库存量
     */
    @Schema(description = "警报库存量")
    private Integer warningAmount;
}
