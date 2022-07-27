package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.service.UserService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yin Zihang
 * @since 2022/7/27 15:39
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return userService.getUserInfoByToken(token);
    }
}
