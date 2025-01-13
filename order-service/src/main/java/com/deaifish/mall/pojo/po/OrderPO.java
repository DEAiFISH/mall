package com.deaifish.mall.pojo.po;

import com.alibaba.fastjson2.JSONObject;
import com.deaifish.mall.converter.StringJSONObjectConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/2 22:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
@Comment("订单表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE orders SET is_delete = 1 WHERE order_id = ?")
public class OrderPO extends BasePO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", columnDefinition = "BIGINT COMMENT '订单ID'")
    private Long orderId;

    /**
     * 用户ID
     */
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '用户ID'")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT NOT NULL COMMENT '商品ID'")
    private Long productId;

    /**
     * 特有规格参数（{属性：参数}）
     */
    @Column(name = "parameter", columnDefinition = "VARCHAR(512) COMMENT '特有规格参数（{属性：参数}）'")
    @Convert(converter = StringJSONObjectConverter.class)
    private JSONObject parameter;

    /**
     * 订单流水号
     */
    @Column(name = "number", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '订单流水号'")
    private String number;

    /**
     * 订单金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "DOUBLE NOT NULL COMMENT '订单金额'")
    private Double money;

    /**
     * 支付方式（微信：0；支付宝：1）
     */
    @Column(name = "payment_method", columnDefinition = "TINYINT NOT NULL COMMENT '支付方式（微信：0；支付宝：1）'")
    private Byte paymentMethod;

    /**
     * 订单备注
     */
    @Column(name = "memo", columnDefinition = "VARCHAR(128) COMMENT '订单备注'")
    private String memo;

    /**
     * 状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）
     */
    @Column(name = "status", columnDefinition = "TINYINT NOT NULL DEFAULT 1 COMMENT '状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）'")
    private Byte status;

    /**
     * 地址ID
     */
    @Column(name = "address_id", columnDefinition = "BIGINT NOT NULL COMMENT '地址ID'")
    private Long addressId;

    /**
     * 快递单号
     */
    @Column(name = "courier_number", columnDefinition = "VARCHAR(32) COMMENT '快递单号'")
    private String courierNumber;

    /**
     * 商品数量
     */
    @Column(name = "amount", columnDefinition = "INT NOT NULL COMMENT '商品数量'")
    private Integer amount;

    /**
     * 付款时间
     */
    @Column(name = "pay_time", columnDefinition = "DATETIME COMMENT '付款时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "ship_time", columnDefinition = "DATETIME COMMENT '发货时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date shipTime;

    /**
     * 完成时间
     */
    @Column(name = "finish_time", columnDefinition = "DATETIME COMMENT '完成时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;

    /**
     * 取消时间
     */
    @Column(name = "cancel_time", columnDefinition = "DATETIME COMMENT '取消时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;

    /**
     * 是否已经支付（1：已支付，0：未支付）
     */
    @Column(name = "is_pay", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否已经支付（TRUE：已支付，FALSE：未支付）'")
    private Boolean isPay;

    /**
     * 删除状态（0：没有删除，1：回收站，2：永久删除）
     */
    @Column(name = "is_delete", columnDefinition = "TINYINT NOT NULL DEFAULT 0 COMMENT '删除状态（0：没有删除，1：回收站，2：永久删除）'")
    private Byte isDelete;

    /**
     * 关闭原因（1-超时未支付 2-退款关闭 3-买家取消）
     */
    @Column(name = "cancel_reason", columnDefinition = "TINYINT COMMENT '关闭原因（1-超时未支付 2-退款关闭 3-买家取消）'")
    private Byte cancelReason;
}
