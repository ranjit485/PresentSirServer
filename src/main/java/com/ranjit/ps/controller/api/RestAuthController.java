package com.ranjit.ps.controller.api;

import com.ranjit.ps.model.LoginRequest;
import com.ranjit.ps.security.JwtTokenUtil;
import com.ranjit.ps.service.AuthService;
import com.ranjit.ps.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RestAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            String token = jwtTokenUtil.generateToken(userDetails.getUsername());
            if (token == null || token.isEmpty()) {
                throw new IllegalStateException("Failed to generate token");
            }

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Log other exceptions for further debugging
            e.printStackTrace();
            return new ResponseEntity<>("Authentication process failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
