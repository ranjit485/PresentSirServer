package com.ranjit.ps.service;

import com.ranjit.ps.model.Role;
import com.ranjit.ps.model.User;
import com.ranjit.ps.repository.RoleRepository;
import com.ranjit.ps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new role
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get a role by ID
    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    // Update a role
    public Role updateRole(Long roleId, Role roleDetails) {
        return roleRepository.findById(roleId).map(role -> {
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        }).orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
    }

    // Delete a role
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    // Assign a role to a user
    public User assignRoleToUser(String email, Long roleId) {
        Optional<User> userOptional = userRepository.findById(email);
        Optional<Role> roleOptional = roleRepository.findById(roleId);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();
            user.getRoles().add(role);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User or Role not found.");
        }
    }
}
