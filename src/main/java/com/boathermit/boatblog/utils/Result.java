package com.boathermit.boatblog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.boathermit.boatblog.enums.ResultCode;

/**
 * API统一返回结果类
 *
 * @author yinzihang
 * @since 0.1
 */
@Data
@AllArgsConstructor
public class Result {

    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static Result failed() {
        return new Result(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg());
    }

    public static Result failed(ResultCode resultCode) {
        return new Result(resultCode.getCode(), resultCode.getMsg());
    }

    public static Result failed(ResultCode resultCode, Object data) {
        return new Result(resultCode.getCode(), resultCode.getMsg(), data);
    }
}
