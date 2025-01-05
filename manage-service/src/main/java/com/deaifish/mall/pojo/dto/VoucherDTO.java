package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @description 优惠卷 DTO
 *
 * @date 2024/12/14 19:06
 */
@Data
public class VoucherDTO {

    /**
     * 优惠卷ID
     */
    @Schema(description = "优惠卷ID")
    @NotNull(message = "优惠卷ID不能为空", groups = UpdateGroup.class)
    private Long voucherId;

    /**
     * 优惠卷名称
     */
    @Schema(description = "优惠卷名称")
    @NotBlank(message = "优惠卷名称不能为空")
    @Length(max = 31, message = "优惠卷名称长度不能超过{max}")
    private String name;

    /**
     * 优惠卷描述
     */
    @Schema(description = "优惠卷描述")
    @Length(max = 255, message = "优惠卷描述长度不能超过{max}")
    private String description;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    @NotNull(message = "优惠金额不能为空")
    private Double price;

    /**
     * 余量
     */
    @Schema(description = "优惠卷余量")
    @NotNull(message = "余量不能为空")
    private Integer amount;
}
