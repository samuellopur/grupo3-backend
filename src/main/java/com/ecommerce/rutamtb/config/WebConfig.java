package com.ecommerce.rutamtb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                    "https://master.d1madz5fpdj89j.amplifyapp.com",
                    "https://8mq33rknsp.us-east-1.awsapprunner.com",
                    "http://localhost:*",
                    "http://127.0.0.1:*",
                    "https://localhost:*"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Content-Type", "Accept", "X-Requested-With", "Cache-Control")
                .allowCredentials(true)
                .maxAge(3600);
    }
}