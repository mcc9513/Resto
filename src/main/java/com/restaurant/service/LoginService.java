package com.restaurant.service;

import com.restaurant.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * The LoginService class provides functionality for user registration and authentication.
 * It uses a simple in-memory storage (HashMap) to maintain user records.
 */
public class LoginService {
    // In-memory storage for user records, where the key is the user ID
    private Map<Integer, User> users = new HashMap<>();

    /**
     * Registers a new user in the system.
     *
     * @param user The User object containing the user's details
     * @return true if registration is successful, false if the user ID already exists
     */
    public boolean registerUser(User user) {
        // Check if a user with the same ID already exists
        if (users.containsKey(user.getId())) {
            return false; // Registration failed: user ID already exists
        }
        // Store the user object directly
        // Note: In a real-world scenario, we would hash the password here
        users.put(user.getId(), user);
        return true; // Registration successful
    }

    /**
     * Authenticates a user based on their ID and password.
     *
     * @param id The user's ID
     * @param password The user's password (plain text for this example)
     * @return true if authentication is successful, false otherwise
     */
    public boolean login(int id, String password) {
        // Retrieve the user with the given ID
        User user = users.get(id);
        if (user == null) {
            return false; // Authentication failed: user not found
        }
        // Compare the input password with the stored password
        // Note: In a real-world scenario, we would hash the input password and compare hashes
        return user.getPasswordHash().equals(password);
    }
}