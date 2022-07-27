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

    private String code;
    private String msg;
    private Object data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static Result failed() {
        return new Result(ResultCode.FAILED);
    }
}
