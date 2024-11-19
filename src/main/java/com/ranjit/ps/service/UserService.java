package com.ranjit.ps.service;

import com.ranjit.ps.model.Role;
import com.ranjit.ps.model.User;
import com.ranjit.ps.model.Bus;
import com.ranjit.ps.repository.RoleRepository;
import com.ranjit.ps.repository.UserRepository;
import com.ranjit.ps.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BusRepository busRepository;

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User registerUser(User user, Long busId) {
        // Hash the password
        user.setPassword(encoder.encode(user.getPassword()));

        // Set the Bus entity for the User
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        user.setBus(bus);

        // Assign the default "User" role
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role 'User' not found"));
        user.getRoles().add(defaultRole);

        // Save the User
        return userRepository.save(user);
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findById(email);
    }

    public User updateUser(String email, User userDetails) {
        return userRepository.findById(email).map(user -> {
            user.setName(userDetails.getName());
            user.setBus(userDetails.getBus());
            user.setContact(userDetails.getContact());
            user.setGender(userDetails.getGender());
            user.setPassword(encoder.encode(userDetails.getPassword()));
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public User assignBusToUser(String email, Long busId) {
        Optional<User> userOptional = userRepository.findById(email);
        Optional<Bus> busOptional = busRepository.findById(busId);

        if (userOptional.isPresent() && busOptional.isPresent()) {
            User user = userOptional.get();
            user.setBus(busOptional.get());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User or Bus not found.");
        }
    }
}
