package com.deaifish.mall.pojo.contanst;

/**
 * @description 状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）
 *
 * @author DEAiFISH
 * @date 2025/1/12 19:03
 */
public enum OrderStatus {
    WAIT_PAY((byte) 1, "待支付"),
    WAIT_SEND((byte) 2, "待发货"),
    WAIT_RECEIVE((byte) 3, "待收货"),
    WAIT_EVALUATE((byte) 4, "待评价"),
    FINISH((byte) 5, "完成"),
    FAIL((byte) 6, "失败");

    private final byte code;
    private final String desc;

    OrderStatus(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(byte code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status.getDesc();
            }
        }
        // 未找到匹配的枚举值，返回 null 或者其他默认值
        return null;
    }
}