package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.po.User;
import com.boathermit.boatblog.utils.Result;

/**
 * @author yinzihang
 * @since 0.1
 */
public interface UserService {

    /**
     * 根据用户id返回对应用户
     * @param id 用户id
     * @return 用户
     */
    User findUserById(Long id);

    /**
     * 通过account和password查找用户
     * @param account 账号
     * @param pwd 密码
     * @return 对应用户
     */
    User findUser(String account, String pwd);

    /**
     * 通过token查找用户
     * @param token token
     * @return 用户
     */
    Result getUserInfoByToken(String token);
}
