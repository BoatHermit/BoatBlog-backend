package com.boathermit.boatblog.handler;

import com.alibaba.fastjson.JSON;
import com.boathermit.boatblog.enums.ResultCode;
import com.boathermit.boatblog.model.po.User;
import com.boathermit.boatblog.service.LoginService;
import com.boathermit.boatblog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yin Zihang
 * @since 2022/7/28 12:21
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private final LoginService loginService;

    @Autowired
    LoginInterceptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestUri = request.getRequestURI();
        log.info("request uri:{}",requestUri);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (token == null){
            Result result = Result.fail(ResultCode.NO_LOGIN);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        User user = loginService.checkToken(token);
        if (user == null){
            Result result = Result.fail(ResultCode.NO_LOGIN);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        //是登录状态，放行
        return true;
    }
}
