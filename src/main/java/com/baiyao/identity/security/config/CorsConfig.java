package com.baiyao.identity.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author baiyao
 * @date 2021/10/29 13:26
 * @description 跨域请求
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 项目中的所有接口都支持跨域
        registry.addMapping("/**")
                // 所有地址都可以访问，也可以配置具体地址
                .allowedOrigins("*")
                .allowCredentials(true)
                // "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
