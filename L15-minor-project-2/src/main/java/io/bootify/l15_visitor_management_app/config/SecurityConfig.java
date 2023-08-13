package io.bootify.l15_visitor_management_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123"));
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrfConfigurer) -> {csrfConfigurer.disable();})
                .authorizeHttpRequests((auth)-> {
                    auth.requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/api/resident/**").hasAuthority("RESIDENT")
                    .requestMatchers("/api/gatekeeper/**").hasAuthority("GATEKEEPER").anyRequest()
                            .authenticated();
        })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();

    }
}
