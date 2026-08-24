package com.devcanvas.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI portfolioOpenAPI() {

        return new OpenAPI()
                .info(
                    new Info()
                        .title("Developer Portfolio API")
                        .version("1.0")
                        .description(
                            "REST API for my personal developer portfolio"
                        )
                );
    }
}
