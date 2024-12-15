package com.library.lms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.lms.entity.User;
import com.library.lms.repository.UserRepository;

import java.util.Optional;
@Service
public class UserService {
	  @Autowired
	    private UserRepository userRepository;

	    public User createUser(User user) {
	        return userRepository.save(user);
	    }

	    public Optional<User> getUserById(Long id) {
	        return userRepository.findById(id);
	    }

	    public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }
	    public User loginUser(String email, String password) { // Highlighted
	        User user = userRepository.findByEmail(email);
	        if (user != null && user.getPassword().equals(password)) {
	            return user;
	        } else {
	            throw new RuntimeException("Invalid email or password");
	        }
	    }

	    public User updateUser(Long id, User updatedUser) { // Highlighted: Using PUT to update the entire user object
	        return userRepository.findById(id).map(user -> {
	            if (updatedUser.getName() != null) {
	                user.setName(updatedUser.getName());
	            }
	            if (updatedUser.getPassword() != null) {
	                user.setPassword(updatedUser.getPassword());
	            }
	            // Optionally, you can update other fields if required, e.g., email, etc.
	            return userRepository.save(user);
	        }).orElseThrow(() -> new RuntimeException("User not found"));
	    }
}
