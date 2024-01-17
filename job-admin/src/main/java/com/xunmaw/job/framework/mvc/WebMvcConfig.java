package com.xunmaw.job.framework.mvc;

import com.xunmaw.job.utils.UploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UploadConfig uploadConfig;

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login", "/upload/**", "/f/**");
    }

    //使用CorsFilter解决跨域问题
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域请求的域名
        corsConfiguration.addAllowedOrigin("*");
        //允许跨域请求的方法
        corsConfiguration.addAllowedMethod("*");
        //允许任何头部
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        return corsFilter;
    }

    //添加资源映射处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadConfig.getAccessPath())
                .addResourceLocations("file:"+uploadConfig.getUploadFolder());
    }
}
