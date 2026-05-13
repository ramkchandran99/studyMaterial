package com.ram.spring.ai.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring AI")
                        .version("1.0")
                        .description("Spring AI API documentation")
                        .contact(new Contact()
                                .name("Ram")
                                .email("ramkchandran99@gmail.com")));
    }
}