package com.airbnb.config;

import com.auth0.jwt.JWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SeurityConfig {
    private JwtRequestFilter jwtRequestFilter;

    public SeurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
//        requestMatchers("/api/v1/users/adduser", "/api/v1/users/login")
//                .permitAll()
//                .requestMatchers("/api/v1/countries/addcountry").hasRole("ADMIN")
//                .requestMatchers("/api/v1/users/profile").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated();

        return http.build();
    }

}
