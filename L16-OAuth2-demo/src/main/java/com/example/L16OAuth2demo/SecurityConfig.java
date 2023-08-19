package com.example.L16OAuth2demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(
                        a -> a.requestMatchers("/", "/webjars/**").permitAll()
                                .anyRequest().authenticated()
                ).csrf( c -> c.disable())
                .logout(l -> l.logoutSuccessUrl("/").permitAll())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
