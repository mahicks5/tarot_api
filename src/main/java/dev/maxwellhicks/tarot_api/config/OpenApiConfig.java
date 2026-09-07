package dev.maxwellhicks.tarot_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI tarotApiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tarot API")
                        .description("A RESTful API for drawing tarot cards, built with Spring Boot.")
                        .version("1.0.0"));
    }

}
