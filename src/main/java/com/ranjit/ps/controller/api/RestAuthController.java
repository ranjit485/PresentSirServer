package com.ranjit.ps.controller.api;

import com.ranjit.ps.model.User;
import com.ranjit.ps.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Presentsir/api/auth")
public class RestAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestParam User user) {
        return authService.registerUser(user);
    }



    // Add login and JWT generation logic here
}