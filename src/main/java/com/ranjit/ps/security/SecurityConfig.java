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
                .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/Presentsir/api/auth**").permitAll()  // Allow unauthenticated access to auth endpoints
                .requestMatchers("/Presentsir/register", "/login").permitAll()  // Allow unauthenticated access to login and register
                .requestMatchers("/Presentsir/api/users**").hasRole("ADMIN") // Access restricted to ADMIN role
                .requestMatchers("/Presentsir/api/roles**").hasRole("ADMIN") // Access restricted to ADMIN role
                .requestMatchers("/Presentsir/api/buses**").hasRole("ADMIN") // Access restricted to ADMIN role
                .requestMatchers("/Presentsir**").hasRole("ADMIN") // Access restricted to ADMIN role
                .anyRequest().authenticated() // Protect all other endpoints
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .loginProcessingUrl("/login") // Ensure form login is properly processed
                .defaultSuccessUrl("/defaultPage", false) // Redirect to /home after successful login
                .permitAll()
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
