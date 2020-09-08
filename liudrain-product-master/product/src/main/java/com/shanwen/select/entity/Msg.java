package com.shanwen.select.entity;

import lombok.Data;

/**
 * 返回消息格式统一处理
 */
@Data
public class Msg<T> {

    /*状态码  默认1是正确 0是错误*/
    private Integer status;
    /*文字消息 正确或者错误的文字信息*/
    private String message;

    /*返回的内容*/
    private T result;

    /*正确代码*/
    private static final Integer SUCCESS_CODE = 1;
    /*错误代码*/
    private static final Integer ERROR_CODE = 0;


    public Msg() {

    }

    public static <T> Msg<T> success() {
        return success("", null);
    }

    public static <T> Msg<T> success(String message) {
        return success(message, null);
    }

    public static <T> Msg<T> success(T result) {
        return success("", result);
    }

    public static <T> Msg<T> success(String message, T result) {
        Msg<T> tmsg = new Msg<>();
        tmsg.status = SUCCESS_CODE;
        tmsg.message = message;
        tmsg.result = result;
        return tmsg;
    }

    public static <T> Msg<T> error() {
        return error("", null);
    }

    public static <T> Msg<T> error(String message) {
        return error(message, null);
    }

    public static <T> Msg<T> error(T result) {
        return error("", result);
    }

    public static <T> Msg<T> error(String message, T result) {
        Msg<T> tmsg = new Msg<>();
        tmsg.status = ERROR_CODE;
        tmsg.message = message;
        tmsg.result = result;
        return tmsg;
    }


    public Msg<?> status(Integer status) {
        Msg<T> tmsg = new Msg<>();
        tmsg.status = status;
        return tmsg;
    }

    public Msg<T> status(T result) {
        this.result = result;
        return this;
    }
}
