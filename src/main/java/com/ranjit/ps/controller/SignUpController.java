package com.ranjit.ps.controller;

import com.ranjit.ps.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Presentsir/register")
public class SignUpController {

    @GetMapping("/")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User()); // Add empty User model for form binding
        return "reg";
    }


    @PostMapping("/")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        // Here, you would save the user to the database or do some processing
        model.addAttribute("successMessage", "Registration successful!");
        return "registrationSuccess";
    }
}
