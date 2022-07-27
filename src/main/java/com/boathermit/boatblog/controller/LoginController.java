package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.model.param.LoginParam;
import com.boathermit.boatblog.service.LoginService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yin Zihang
 * @since 2022/7/27 14:14
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public Result userLogin(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }
}
