package com.cinquecento.jwtstarter.config;

import com.cinquecento.jwtstarter.util.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAutoConfiguration {

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
}
