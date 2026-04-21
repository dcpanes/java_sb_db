package com.bdconnect.alumnos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    /**
     * Bean de WebClient configurado con la URL base de JSONPlaceholder.
     * Puedes cambiar la baseUrl a la dirección de tu propio microservicio.
     */
    @Bean
    public WebClient jsonPlaceholderWebClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}

