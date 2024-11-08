package com.ranjit.ps.security;

import com.ranjit.ps.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for testing; enable in production as needed
                .authorizeRequests()
                .requestMatchers("/Presentsir/api/auth**").permitAll() // Allow unauthenticated access to auth endpoints
                .requestMatchers("/Presentsir/api/users**").hasRole("ADMIN") // Access restricted to ADMIN role
                .requestMatchers("/Presentsir/register").permitAll()// Access restricted to ADMIN role
                .requestMatchers("/Presentsir/api/roles**").hasRole("ADMIN") // Access restricted to ADMIN role
                .requestMatchers("/Presentsir/api/buses**").hasRole("ADMIN") // Access restricted to ADMIN role
                .anyRequest().authenticated() // Protect all other endpoints
                .and()
                .httpBasic(); // Use Basic Authentication; change if using JWT

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService()) // Replace with your user details service
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(); // Replace with your UserDetailsService implementation
    }
}
