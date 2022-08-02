package com.boathermit.boatblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
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
            return Result.fail(ResultCode.PARAMS_ERROR);
        }
        String pwd = DigestUtils.md5Hex(password + SLAT);
        User user = userService.findUser(account,pwd);
        if (user == null){
            return Result.fail(ResultCode.ACCOUNT_PWD_NOT_EXIST);
        }
        //登录成功，使用JWT生成token，返回token和redis中
        String token = JwtUtil.createJwt(user);
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result register(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ){
            return Result.fail(ResultCode.PARAMS_ERROR);
        }
        User user = this.userService.findUserByAccount(account);
        if (user != null){
            return Result.fail(ResultCode.ACCOUNT_EXIST);
        }
        user = new User();
        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password+SLAT));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setAvatar("/static/img/logo.b3a48c0.png");
        user.setAdmin(1);
        user.setDeleted(0);
        user.setSalt("");
        user.setStatus("");
        user.setEmail("");
        this.userService.save(user);

        String token = JwtUtil.createJwt(user);
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public User checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Claim> record = JwtUtil.parseJwt(token);
        if (record == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }

        return JSON.parseObject(userJson, User.class);
    }
}
