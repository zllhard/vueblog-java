package com.zll.vueblog.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * Result类，这个用于我们的异步统一返回的结果封装
 */
@Data
public class Result implements Serializable {

    /*
    结果中包含状态码、结果消息、数据
     */
    private int code; // 200成功，非200不成功
    private String msg;
    private Object data;

    public static Result success(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }
}
