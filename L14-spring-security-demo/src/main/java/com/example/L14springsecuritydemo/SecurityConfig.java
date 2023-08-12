package com.example.L14springsecuritydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.builder().username("rahul").password(passwordEncoder().encode("abc123"))
//                .roles("USER").build();
//
//        UserDetails user2 = User.builder().username("ajay").password(passwordEncoder().encode("ajay123"))
//                .roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("abc"));
    }
    //$2a$10$e8nxeZJ3fvqzhch6MSBxFucer3ISTEtnI0G1tiN7.1yrLmHKOo7tS
    //$2a$10$Qpnj6qWGyWzxl2Gh9RWbVuDZr1ltqyAYxob3QdJj0qARG8kjDiYUG
    //$2a$10$RHqqh7tSyD.5dcU4PDZd2eGWy6FOPTmVhOr1R6oK0i/35Z1mo9MkS


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrfConfigurer) -> csrfConfigurer.disable())
                .authorizeHttpRequests((auth) ->{
                    auth.requestMatchers("/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/public/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }


}
