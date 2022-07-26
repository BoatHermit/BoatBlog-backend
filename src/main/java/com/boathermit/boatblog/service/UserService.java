package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.po.User;

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
}
