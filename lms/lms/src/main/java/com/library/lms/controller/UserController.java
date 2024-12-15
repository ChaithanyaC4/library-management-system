package com.library.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.lms.entity.LoginRequest;
import com.library.lms.entity.User;
import com.library.lms.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
 
    @PostMapping("/login") // Highlighted
    public User loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    @PutMapping("/update/{id}") // Highlighted: Using PUT for updating a user
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
    @GetMapping("/session/validate") // Highlighted: Validate user session
    public String validateSession(@RequestParam String email) {
        Optional<User> user = userService.getUserById(Long.parseLong(email)); // Simple check; improve for real session validation
        if (user.isPresent()) {
            return "Session is valid.";
        } else {
            return "Session is invalid.";
        }
    }
}


