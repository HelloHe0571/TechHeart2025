package com.core.TecHeart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器注册
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {


//    /**
//     * 把我们定义的拦截器类注册为Bean
//     */
//    @Bean
//    public HandlerInterceptor getInterceptor() {
//
//        return new UserInterceptor();
//    }
//
//    /**
//     * 添加拦截器，并配置拦截地址
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        List<String> pathPatterns = new ArrayList<>();
//        pathPatterns.add("/login");
//        pathPatterns.add("/register");
//        registry.addInterceptor(getInterceptor())
//                .excludePathPatterns(pathPatterns);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 允许前端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true) // 允许携带凭证（如 Cookies）
                .maxAge(3600); // 预检请求缓存时间（秒）
    }

}
