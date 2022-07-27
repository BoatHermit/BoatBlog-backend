package com.boathermit.boatblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boathermit.boatblog.dao.UserMapper;
import com.boathermit.boatblog.enums.ResultCode;
import com.boathermit.boatblog.model.po.User;
import com.boathermit.boatblog.model.vo.LoginUserVo;
import com.boathermit.boatblog.service.UserService;
import com.boathermit.boatblog.utils.JwtUtil;
import com.boathermit.boatblog.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yinzihang
 * @since 0.1
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    UserServiceImpl(UserMapper userMapper, RedisTemplate<String, String> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
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

    @Override
    public Result getUserInfoByToken(String token) {
        Map<String, Claim> map = JwtUtil.parseJwt(token);
        if (map == null){
            return new Result(ResultCode.NO_LOGIN);
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return new Result(ResultCode.NO_LOGIN);
        }
        User user = JSON.parseObject(userJson, User.class);
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setAccount(user.getAccount());
        loginUserVo.setAvatar(user.getAvatar());
        loginUserVo.setId(user.getId());
        loginUserVo.setNickname(user.getNickname());
        loginUserVo.setRole(user.getRole());
        return Result.success(loginUserVo);
    }
}
