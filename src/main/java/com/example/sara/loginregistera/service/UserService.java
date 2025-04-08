package com.example.sara.loginregistera.service;

import com.example.sara.loginregistera.model.LoginUser;
import com.example.sara.loginregistera.model.User;
import com.example.sara.loginregistera.Role;
import com.example.sara.loginregistera.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // Method to handle the registration of a new user
    public User validateRegistration(User newUser, BindingResult result) {
        // Check if passwords match
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            result.rejectValue("password", "passwordMismatch", "Passwords do not match");
        }

        // Check if email is already in use
        if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "emailExists", "This email is already registered");
        }

        if (result.hasErrors()) {
            return null;
        }

        // Hash the password before saving
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);

        // Set role: first user will be admin, subsequent users will be "user"
        if (!userRepo.existsByRole(Role.ADMIN)) {
            newUser.setRole(Role.ADMIN); // First user gets admin role
        } else {
            newUser.setRole(Role.USER); // All others are regular users
        }

        // Save the new user and return
        return userRepo.save(newUser);
    }

    // Method to validate user login
    public User validateLogin(LoginUser loginUser, BindingResult result) {
        // Check if email exists in the database
        Optional<User> possibleUser = userRepo.findByEmail(loginUser.getEmail());
        if (!possibleUser.isPresent()) {
            result.rejectValue("email", "invalidCredentials", "Invalid login credentials");
            return null;
        }

        User user = possibleUser.get();

        // Check if the provided password matches the hashed password in the database
        if (!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            result.rejectValue("email", "invalidCredentials", "Invalid login credentials");
            return null;
        }

        return result.hasErrors() ? null : user;
    }

    // Method to fetch a user by ID
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}