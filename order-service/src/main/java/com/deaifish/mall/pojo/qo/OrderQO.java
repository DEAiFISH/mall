package com.deaifish.mall.pojo.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 15:12
 */
@Data
public class OrderQO {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 订单金额上限
     */
    @Schema(description = "订单金额上限")
    private Double moneyMax;

    /**
     * 订单金额下限
     */
    @Schema(description = "订单金额下限")
    private Double moneyMin;

    /**
     * 支付方式（微信：0；支付宝：1）
     */
    @Schema(description = "支付方式（微信：0；支付宝：1）")
    private Byte paymentMethod;

    /**
     * 状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）
     */
    @Schema(description = "状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）")
    private Byte status;

    /**
     * 付款时间（开始时间）
     */
    @Schema(description = "付款时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTimeFrom;

    /**
     * 付款时间（结束时间）
     */
    @Schema(description = "付款时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTimeTo;

    /**
     * 发货时间（开始时间）
     */
    @Schema(description = "发货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipTimeFrom;

    /**
     * 发货时间（结束时间）
     */
    @Schema(description = "发货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipTimeTo;

    /**
     * 完成时间（开始时间）
     */
    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTimeFrom;

    /**
     * 完成时间（结束时间）
     */
    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTimeTo;

    /**
     * 取消时间（开始时间）
     */
    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTimeFrom;

    /**
     * 取消时间（结束时间）
     */
    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTimeTo;

    /**
     * 是否已经支付（1：已支付，0：未支付）
     */
    @Schema(description = "是否已经支付（1：已支付，0：未支付）")
    private Boolean isPay;

    /**
     * 关闭原因（1-超时未支付 2-用户主动取消 3-商家取消）
     */
    @Schema(description = "关闭原因（1-超时未支付 2-用户主动取消 3-商家取消）")
    private Byte cancelReason;

    /**
     * 是否删除（2：永久删除，1：已删除，0：未删除）
     */
    @Schema(description = "是否删除（2：永久删除，1：已删除，0：未删除）")
    private Byte isDelete;
}