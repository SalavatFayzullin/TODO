package ru.salavat.todo_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
                .requestMatchers("/register**", "/login").permitAll()
                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Укажите кастомную страницу логина
                        .loginProcessingUrl("/perform_login") // URL для обработки формы
                        .usernameParameter("username") // Параметр имени пользователя
                        .passwordParameter("password") // Параметр пароля
                        .failureUrl("/login?error=true") // Перенаправление при ошибке
                        .defaultSuccessUrl("/tasks", true))
                .userDetailsService(userDetailsService)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .build();
    }
}
