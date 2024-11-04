package com.ranjit.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = "com.ranjit.ps")
@Configuration
@EnableWebMvc
public class AppConfig {

}
