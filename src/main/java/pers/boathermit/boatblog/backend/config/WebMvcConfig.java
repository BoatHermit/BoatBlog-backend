package pers.boathermit.boatblog.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC通用配置
 * @author yinzihang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] ORIGINS =
            new String[] {
                    "localhost:8000",
                    "127.0.0.1:8000",
                    "127.0.0.1:8080",
                    "localhost:8080",
                    "127.0.0.1",
            };
    private static final String[] METHODS =
            new String[] { "GET", "POST", "PUT", "DELETE" };

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
}