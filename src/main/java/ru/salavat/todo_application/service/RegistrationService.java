package ru.salavat.todo_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.salavat.todo_application.dto.RegistrationDto;
import ru.salavat.todo_application.model.MyUser;
import ru.salavat.todo_application.repo.UserRepository;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegistrationDto registrationDto) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        MyUser user = new MyUser();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        userRepository.save(user);
    }
}