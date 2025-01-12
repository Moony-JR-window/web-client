package com.example.webClient.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection if needed (for testing purposes)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // Allow Swagger UI and API docs
                        .requestMatchers("/v1/register/**").permitAll() // Allow public access to registration endpoints
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .formLogin(form -> form.disable()); // Disable form login if not needed

        return http.build();
    }
}
