package com.boathermit.boatblog.aspect;

import com.alibaba.fastjson.JSON;
import com.boathermit.boatblog.annotation.Log;
import com.boathermit.boatblog.utils.HttpContextUtil;
import com.boathermit.boatblog.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 * @author Yin Zihang
 * @since 2022/8/4 9:15
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.boathermit.boatblog.annotation.Log)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        recordLog(point, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        LogAspect.log.info("=======================================log start=======================================");
        LogAspect.log.info("module:{}", log.module());
        LogAspect.log.info("operation:{}", log.operation());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        LogAspect.log.info("request method:{}",className + "." + methodName + "()");

//        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        LogAspect.log.info("params:{}",params);

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        LogAspect.log.info("ip:{}", IpUtil.getIpAddr(request));


        LogAspect.log.info("execute time : {} ms",time);
        LogAspect.log.info("========================================log end========================================");
    }
}
