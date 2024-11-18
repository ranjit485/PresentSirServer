package com.ranjit.ps.controller.api;

import com.ranjit.ps.model.User;
import com.ranjit.ps.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@ApiParam(value = "Email of the user to retrieve", required = true) @PathVariable String email) {
        return userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }

    @PutMapping("/{email}")
    public User updateUser(@ApiParam(value = "Email of the user to update", required = true) @PathVariable String email, @RequestBody User userDetails) {
        return userService.updateUser(email, userDetails);
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@ApiParam(value = "Email of the user to delete", required = true) @PathVariable String email) {
        userService.deleteUser(email);
    }

    @PutMapping("/{email}/assign-bus/{busId}")
    public void assignBusToUser(@ApiParam(value = "Email of the user", required = true) @PathVariable String email, @ApiParam(value = "Bus ID to assign", required = true) @PathVariable Long busId) {
        userService.assignBusToUser(email, busId);
    }
}
