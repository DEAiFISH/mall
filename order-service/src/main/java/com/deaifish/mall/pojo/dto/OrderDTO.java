package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/2 22:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    @NotNull(message = "订单ID不能为空", groups = UpdateGroup.class)
    private Long orderId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    /**
     * 特有规格参数（{属性：参数}）
     */
    @Schema(description = "特有规格参数（{属性：参数}）")
    @Length(max = 512, message = "特有规格参数长度不能超过{max}")
    private String parameter;

    /**
     * 订单流水号
     */
    @Schema(description = "订单流水号")
    @NotBlank(message = "订单流水号不能为空")
    @Length(max = 32, message = "订单流水号长度不能超过{max}")
    private String number;

    /**
     * 订单金额
     */
    @Schema(description = "订单金额")
    @NotNull(message = "订单金额不能为空")
    private Double money;

    /**
     * 支付方式（微信：0；支付宝：1）
     */
    @Schema(description = "支付方式（微信：0；支付宝：1）")
    @NotNull(message = "支付方式不能为空")
    private Byte paymentMethod;

    /**
     * 订单备注
     */
    @Schema(description = "订单备注")
    @Length(max = 128, message = "订单备注长度不能超过{max}")
    private String memo;

    /**
     * 状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）
     */
    @Schema(description = "状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）")
    @NotNull(message = "订单状态不能为空")
    private Byte status;

    /**
     * 地址ID
     */
    @Schema(description = "地址ID")
    @NotNull(message = "收货地址不能为空")
    private Long addressId;

    /**
     * 快递单号
     */
    @Schema(description = "快递单号")
    @Length(max = 32, message = "快递单号长度不能超过{max}")
    private String courierNumber;

    /**
     * 商品数量
     */
    @Schema(description = "商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer amount;

    /**
     * 付款时间
     */
    @Schema(description = "付款时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 发货时间
     */
    @Schema(description = "发货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipTime;

    /**
     * 完成时间
     */
    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 取消时间
     */
    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

    /**
     * 是否已经支付（1：已支付，0：未支付）
     */
    @Schema(description = "是否已经支付（1：已支付，0：未支付）")
    @NotNull(message = "是否已经支付不能为空")
    private Boolean isPay;

    /**
     * 删除状态（0：没有删除，1：回收站，2：永久删除）
     */
    @Schema(description = "删除状态（0：没有删除，1：回收站，2：永久删除）")
    @NotNull(message = "删除状态不能为空")
    private Byte isDelete;

    /**
     * 关闭原因（1-超时未支付 2-退款关闭 3-买家取消）
     */
    @Schema(description = "关闭原因（1-超时未支付 2-退款关闭 3-买家取消）")
    private Byte cancelReason;
}
