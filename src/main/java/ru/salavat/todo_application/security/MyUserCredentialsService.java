package ru.salavat.todo_application.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserCredentialsService implements UserDetailsService {
    private UserDetails admin;
    private UserDetails user;
    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        admin = User.builder().username("admin").password(encoder.encode("1987")).build();
        user = User.builder().username("user").password(encoder.encode("1987")).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) return admin;
        else return user;
    }
}
