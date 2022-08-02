package com.boathermit.boatblog.config;

import com.boathermit.boatblog.handler.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC通用配置
 *
 * @author yinzihang
 * @since 0.1
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] ORIGINS =
            new String[] {
                    "http://localhost:8080",
                    "https://localhost:8080",
            };
    private static final String[] METHODS =
            new String[] { "GET", "POST", "PUT", "DELETE" };

    private final LoginInterceptor loginInterceptor;

    WebMvcConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ORIGINS)
                .allowCredentials(true)
                .allowedMethods(METHODS)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/comments/create/change");
    }
}