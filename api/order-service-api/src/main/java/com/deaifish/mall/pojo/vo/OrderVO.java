package com.deaifish.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/2 22:56
 */
@Data
@Builder
public class OrderVO {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片地址
     */
    private String productImage;

    /**
     * 特有规格参数（{属性：参数}）
     */
    private String parameter;

    /**
     * 订单流水号
     */
    private String number;

    /**
     * 订单金额
     */
    private Double money;

    /**
     * 支付方式（微信：0；支付宝：1）
     */
    private Byte paymentMethod;

    /**
     * 订单备注
     */
    private String memo;

    /**
     * 状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）
     */
    private String status;

    /**
     * 地址ID
     */
    private Long addressId;

    /**
     * 地址（省市区+详细地址）
     */
    private String address;

    /**
     * 快递单号
     */
    private String courierNumber;

    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date shipTime;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;

    /**
     * 取消时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;

    /**
     * 是否已经支付（1：已支付，0：未支付）
     */
    private Boolean isPay;

    /**
     * 删除状态（0：没有删除，1：回收站，2：永久删除）
     */
    private Byte isDelete;

    /**
     * 关闭原因（1-超时未支付 2-用户主动取消 3-商家取消）
     */
    private String cancelReason;
}
