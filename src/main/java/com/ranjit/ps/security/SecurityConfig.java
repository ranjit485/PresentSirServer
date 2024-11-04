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
                .csrf().disable() // Disable CSRF for testing; you may want to enable it in production
                .authorizeRequests()
                .requestMatchers("/Presentsir/api/auth**").permitAll() // Allow unauthenticated access to auth endpoints
                .requestMatchers("/Presentsir/api/users**").hasAnyRole("ROLE_ADMIN") // Allow unauthenticated access to auth endpoints
                .requestMatchers("/Presentsir/api/roles**").hasAnyRole("ROLE_ADMIN") // Allow unauthenticated access to auth endpoints
                .requestMatchers("/Presentsir/api/buses**").hasAnyRole("ROLE_ADMIN") // Allow unauthenticated access to auth endpoints
                .anyRequest().authenticated() // Protect all other endpoints
                .and()
                .httpBasic(); // Use Basic Authentication; change this if you're using JWT

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

    // Define your UserDetailsService bean here if not already defined
     @Bean
     public UserDetailsService userDetailsService() {
         return new MyUserDetailsService(); // Replace with your UserDetailsService implementation
     }
}
