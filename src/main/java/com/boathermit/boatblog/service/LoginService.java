package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.param.LoginParam;
import com.boathermit.boatblog.utils.Result;

/**
 * @author Yin Zihang
 * @since 2022/7/27 14:33
 */
public interface LoginService {
    /**
     * 登录
     * @param loginParam 登录参数，包含账号，密码
     * @return token
     */
    Result login(LoginParam loginParam);
}
