package ru.salavat.todo_application.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.salavat.todo_application.dto.RegistrationDto;
import ru.salavat.todo_application.service.RegistrationService;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") @Valid RegistrationDto registrationDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            registrationService.register(registrationDto);
        } catch (IllegalArgumentException e) {
            result.rejectValue("username", "error.user", e.getMessage());
            return "registration";
        }

        return "redirect:/login?registered";
    }
}