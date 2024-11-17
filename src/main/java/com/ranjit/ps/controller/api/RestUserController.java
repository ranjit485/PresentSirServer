package com.ranjit.ps.controller.api;

import com.ranjit.ps.model.User;
import com.ranjit.ps.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User Management")
@RestController
@RequestMapping("/Presentsir/api/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create a new user", notes = "Only an ADMIN can create a new user")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @ApiOperation(value = "Get all users", notes = "Users with 'ADMIN' or 'USER' roles can access this")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Get user by email", notes = "Only an ADMIN can view user details by email")
    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserByEmail(@ApiParam(value = "Email of the user to retrieve", required = true) @PathVariable String email) {
        return userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }

    @ApiOperation(value = "Update user by email", notes = "Only an ADMIN can update user details")
    @PutMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@ApiParam(value = "Email of the user to update", required = true) @PathVariable String email, @RequestBody User userDetails) {
        return userService.updateUser(email, userDetails);
    }

    @ApiOperation(value = "Delete user by email", notes = "Only an ADMIN can delete a user")
    @DeleteMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@ApiParam(value = "Email of the user to delete", required = true) @PathVariable String email) {
        userService.deleteUser(email);
    }

    @ApiOperation(value = "Assign bus to user", notes = "Only an ADMIN can assign a bus to a user")
    @PutMapping("/{email}/assign-bus/{busId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void assignBusToUser(@ApiParam(value = "Email of the user", required = true) @PathVariable String email, @ApiParam(value = "Bus ID to assign", required = true) @PathVariable Long busId) {
        userService.assignBusToUser(email, busId);
    }
}
