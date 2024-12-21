package com.deaifish.mall.response;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 14:20
 */
@Slf4j
public class R<T> implements Serializable {

    /**
     * 状态码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return Objects.equals(ResponseEnum.OK.value(), this.code);
    }

    @Override
    public String toString() {
        return "R{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    public static <T> R<T> success(T data) {
        R<T> res = new R<>();
        res.setData(data);
        res.setCode(ResponseEnum.OK.value());
        return res;
    }

    public static <T> R<T> success() {
        R<T> res = new R<>();
        res.setCode(ResponseEnum.OK.value());
        res.setMsg(ResponseEnum.OK.getMsg());
        return res;
    }

    /**
     * 前端显示失败消息
     * @param msg 失败消息
     * @return
     */
    public static <T> R<T> showFailMsg(String msg) {
        log.error(msg);
        R<T> res = new R<>();
        res.setMsg(msg);
        res.setCode(ResponseEnum.SHOW_FAIL.value());
        return res;
    }

    public static <T> R<T> fail(ResponseEnum responseEnum) {
        log.error(responseEnum.toString());
        R<T> res = new R<>();
        res.setMsg(responseEnum.getMsg());
        res.setCode(responseEnum.value());
        return res;
    }

    public static <T> R<T> fail(ResponseEnum responseEnum, T data) {
        log.error(responseEnum.toString());
        R<T> res = new R<>();
        res.setMsg(responseEnum.getMsg());
        res.setCode(responseEnum.value());
        res.setData(data);
        return res;
    }

    public static <T> R<T> transform(R<?> oldServerResponseEntity) {
        R<T> res = new R<>();
        res.setMsg(oldServerResponseEntity.getMsg());
        res.setCode(oldServerResponseEntity.getCode());
        log.error(res.toString());
        return res;
    }

}
