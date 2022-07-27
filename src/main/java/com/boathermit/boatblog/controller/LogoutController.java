package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.service.LoginService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yin Zihang
 * @since 2022/7/27 16:02
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    private final LoginService loginService;

    @Autowired
    LogoutController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }
}
