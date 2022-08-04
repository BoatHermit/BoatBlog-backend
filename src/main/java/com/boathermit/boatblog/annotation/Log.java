package com.boathermit.boatblog.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author Yin Zihang
 * @since 2022/8/4 9:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String module() default "";

    String operation() default "";
}
