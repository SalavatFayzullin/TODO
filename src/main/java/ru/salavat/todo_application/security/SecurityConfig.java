package ru.salavat.todo_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(httpRequests ->  httpRequests
                .requestMatchers("/register**").permitAll()
                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .successForwardUrl("/tasks"))
                .userDetailsService(userDetailsService)
                .build();
    }
}
