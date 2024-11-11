package com.ranjit.ps.controller;

import com.ranjit.ps.model.User;
import com.ranjit.ps.repository.BusRepository;
import com.ranjit.ps.service.AuthService;
import com.ranjit.ps.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Presentsir/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private BusRepository busRepository;

    // Show login page
    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        return "success"; // Return the success page
    }

    @GetMapping("/defaultPage")
    public String redirectToHomePage(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))
                ? "redirect:/index" : "redirect:/success";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("buses", busRepository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result,
                               @RequestParam("busId") long busId) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userService.registerUser(user, busId);
            return "redirect:/login";
        } catch (Exception e) {
            result.rejectValue(null, "error.user", "There was an error processing your registration.");
            return "register";
        }
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
                            Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication.getAuthorities()
                    .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    ? "redirect:/Presentsir/index"
                    : "redirect:/Presentsir/success";

        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred.");
            return "login";
        }
    }
}
