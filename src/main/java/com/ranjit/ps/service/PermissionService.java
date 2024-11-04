package com.ranjit.ps.service;

import com.ranjit.ps.model.User;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    public boolean hasAccess(User user, String requiredRole) {
        return user.getRoles().stream().anyMatch(role -> role.getName().equals(requiredRole));
    }
}
