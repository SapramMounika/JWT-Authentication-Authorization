package com.example.jwtsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Disable default in-memory user
        return username -> {
            throw new RuntimeException("UserDetailsService not used");
        };
    }
}
