package com.ranjit.ps.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/secured")
    public String securedEndpoint() {
        return "You have accessed a secured endpoint!";
    }
}
