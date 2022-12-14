package com.example.springsecurityapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
//    @Value("/home/tulen/Downloads/FinalprojectEliseeva/uploads/")
//    private String uploadPath;

    @Value("//home/tulen/Downloads/FinalprojectEliseeva/FinalProjectApplication/src/main/resources/static/img")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");

//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("file://" + imgPath + "/");
    }

}

