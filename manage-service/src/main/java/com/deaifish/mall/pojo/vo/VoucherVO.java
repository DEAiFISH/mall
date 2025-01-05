package com.deaifish.mall.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description 优惠卷
 *
 * @date 2024/12/14 19:06
 */
@Data
public class VoucherVO {

    /**
     * 优惠卷ID
     */
    @Schema(description = "优惠卷ID")
    private Long voucherId;

    /**
     * 优惠卷名称
     */
    @Schema(description = "优惠卷名称")
    private String name;

    /**
     * 优惠卷描述
     */
    @Schema(description = "优惠卷描述")
    private String description;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private Double price;

    /**
     * 余量
     */
    @Schema(description = "优惠卷余量")
    private Integer amount;
}
