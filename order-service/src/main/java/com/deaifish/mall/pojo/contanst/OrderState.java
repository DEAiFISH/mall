package com.deaifish.mall.pojo.contanst;

/**
 * @description 状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）
 *
 * @author DEAiFISH
 * @date 2025/1/12 19:03
 */
public enum OrderState {
    WAIT_PAY((byte) 1, "待支付"),
    WAIT_SEND((byte) 2, "待发货"),
    WAIT_RECEIVE((byte) 3, "待收货"),
    WAIT_EVALUATE((byte) 4, "待评价"),
    FINISH((byte) 5, "完成"),
    FAIL((byte) 6, "失败");

    private final Byte code;
    private final String desc;

    OrderState(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}