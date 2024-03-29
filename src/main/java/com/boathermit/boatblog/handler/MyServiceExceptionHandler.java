package com.boathermit.boatblog.handler;

import com.boathermit.boatblog.exception.MyServiceException;
import com.boathermit.boatblog.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author Yin Zihang
 * @since 2022/7/26 19:07
 */
@ControllerAdvice
public class MyServiceExceptionHandler {

    /**
     * 进行异常处理，处理Exception.class的异常
     * @param e 异常
     * @return 返回失败信息
     */
    @ExceptionHandler(MyServiceException.class)
    @ResponseBody
    public Result doException(MyServiceException e){
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }
}
