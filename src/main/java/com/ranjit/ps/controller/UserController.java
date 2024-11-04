package com.ranjit.ps.controller;

import com.ranjit.ps.model.User;
import com.ranjit.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Presentsir/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }

    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User userDetails) {
        return userService.updateUser(email, userDetails);
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }

    @PutMapping("/{email}/assign-bus/{busId}")
    public void assignBusToUser(@PathVariable String email, @PathVariable Long busId) {
        userService.assignBusToUser(email, busId);
    }
}
