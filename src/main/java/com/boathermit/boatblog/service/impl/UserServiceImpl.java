package com.boathermit.boatblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boathermit.boatblog.dao.UserMapper;
import com.boathermit.boatblog.model.po.User;
import com.boathermit.boatblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yinzihang
 * @since 0.1
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            user = new User();
            user.setNickname("查无此人");
        }
        return user;
    }

    @Override
    public User findUser(String account, String pwd) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,account);
        queryWrapper.eq(User::getPassword,pwd);
        queryWrapper.select(User::getId,User::getAccount,User::getAvatar,User::getNickname, User::getRole);
        queryWrapper.last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }
}
