package com.example.marvelcomics.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI marvelComicsAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Marvel Comics API")
                        .description("Spring Boot REST API for managing Marvel characters")
                        .version("1.0"));
    }
} 