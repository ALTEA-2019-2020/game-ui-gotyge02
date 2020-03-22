package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

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
    RestTemplate trainerApiRestTemplate(){
        var restTemplate = new RestTemplate();
        var basicAuthInterceptor = new BasicAuthenticationInterceptor(username, password);
        restTemplate.getInterceptors().add(basicAuthInterceptor);
        return restTemplate;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}