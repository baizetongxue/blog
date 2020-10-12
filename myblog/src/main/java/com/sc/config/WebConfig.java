package com.sc.config;

import com.sc.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.Arrays;

/**
 * Created by BaiZe schoolmate on 2020/5/10 17:46.
 */


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(Arrays.asList("/admin","/admin/login","/lib/**"));

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File("/springboot/");
        if (!file.exists()){
            file.mkdirs();
        }
        registry.addResourceHandler("/springboot/**")
                .addResourceLocations("file:/springboot/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//            registry.addViewController("/").setViewName("index");
//            registry.addViewController("/index.html").setViewName("index");
//    }
}
