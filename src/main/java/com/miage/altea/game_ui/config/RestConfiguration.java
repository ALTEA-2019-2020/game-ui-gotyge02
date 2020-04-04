package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {
    private String username;
    private String password;

    @Value("${trainer.service.user}")
    void setUsername(String username) {
        this.username = username;
    }

    @Value("${trainer.service.mdp}")
    void setPassword(String password) {
        this.password = password;
    }

    @Bean
    RestTemplate trainerApiRestTemplate() {
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new BasicAuthenticationInterceptor(username, password);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(clientHttpRequestInterceptor));
        return restTemplate;
    }

    @Bean
    RestTemplate pokemonApiRestTemplate() {
        return new RestTemplate();
    }
}