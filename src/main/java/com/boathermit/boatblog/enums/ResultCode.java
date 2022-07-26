package com.boathermit.boatblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API返回的状态码表
 *
 * @author yinzihang
 * @since 0.1
 */
@AllArgsConstructor
@Getter
public enum ResultCode {

    // 请求成功
    SUCCESS(200, "请求成功"),
    // 请求失败
    FAILED(201, "操作失败"),
    // token失效
    TOKEN_FAILED(202, "token失效"),
    // 系统异常
    SYSTEM_FAILED(-999,"系统异常");

    private final int code;
    private final String msg;
}
