package com.ranjit.ps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ranjit.ps")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
