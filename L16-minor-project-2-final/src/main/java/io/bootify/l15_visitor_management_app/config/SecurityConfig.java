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

/*

https://accounts.google.com/o/oauth2/v2/auth?
response_type=code&client_id=388036620207-3uolk1hv6ta7p3r9l6s3bobifh086qe1.apps.googleusercontent.com
&scope=openid%20email%20https://www.googleapis.com/auth/userinfo.profile&
redirect_uri=https://auth.geeksforgeeks.org/googleLogin.php&state=13732496044441a5972c393189edee70
*/