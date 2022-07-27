package com.boathermit.boatblog.exception;

import com.boathermit.boatblog.enums.ResultCode;
import lombok.Getter;

/**
 * 服务器异常
 * @author Yin Zihang
 * @since 2022/7/27 12:49
 */
@Getter
public class MyServiceException extends RuntimeException {
    private int code;

    public MyServiceException(String message, Throwable root) {
        super(message, root);
    }

    public MyServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MyServiceException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
}
