package com.deaifish.mall.pojo.contanst;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 18:10
 */
public enum OrderCancelReason {
    TIMEOUT((byte) 1, "超时未支付"),
    USER((byte) 2, "用户主动取消"),
    MERCHANT((byte) 3, "商家取消");

    private final byte code;
    private final String desc;

    OrderCancelReason(byte code, String desc) {
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
        for (OrderCancelReason status : OrderCancelReason.values()) {
            if (status.getCode() == code) {
                return status.getDesc();
            }
        }
        // 未找到匹配的枚举值，返回 null 或者其他默认值
        return null;
    }
}