package com.smartparking.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Smart Parking API")
                        .version("1.0")
                        .description("API for Smart Parking System"));
    }
}