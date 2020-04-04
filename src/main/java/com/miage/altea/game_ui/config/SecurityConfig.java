package com.miage.altea.game_ui.config;

import com.miage.altea.game_ui.pokemonTypes.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Optional;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private TrainersService trainersService;

    @Autowired
    void setTrainerService(TrainersService trainersService){
        this.trainersService = trainersService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userName -> Optional.ofNullable(trainersService.getTrainer(userName))
                .map(
                        (trainer)->User.withUsername(trainer.getName()).password(trainer.getPassword()).roles("USER").build()
                )
                .orElseThrow(()->new BadCredentialsException("No such user"));
    }
}