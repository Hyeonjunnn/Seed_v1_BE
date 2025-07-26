package com.hyeonjunnn.seed_v1_be.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring API",
                description = "",
                version = "0.0.0"
        )
)
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI();
    }
}
