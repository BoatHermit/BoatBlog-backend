package com.boathermit.boatblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.boathermit.boatblog.enums.ResultCode;
import com.boathermit.boatblog.model.param.LoginParam;
import com.boathermit.boatblog.model.po.User;
import com.boathermit.boatblog.service.LoginService;
import com.boathermit.boatblog.service.UserService;
import com.boathermit.boatblog.utils.JwtUtil;
import com.boathermit.boatblog.utils.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Yin Zihang
 * @since 2022/7/27 14:34
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final String SLAT = "mszlu!@#";

    private final UserService userService;

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    LoginServiceImpl(UserService userService, RedisTemplate<String, String> redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return new Result(ResultCode.PARAMS_ERROR);
        }
        String pwd = DigestUtils.md5Hex(password + SLAT);
        User user = userService.findUser(account,pwd);
        if (user == null){
            return new Result(ResultCode.ACCOUNT_PWD_NOT_EXIST);
        }
        //登录成功，使用JWT生成token，返回token和redis中
        String token = JwtUtil.createJwt(user);
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
