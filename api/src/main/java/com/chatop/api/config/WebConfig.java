package com.chatop.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve files from uploads directory at /uploads/**
    // Serve files from absolute path (relative to project root)
    String absoluteUploadPath = new java.io.File("uploads").getAbsolutePath() + "/";
    registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:" + absoluteUploadPath);
    }
}