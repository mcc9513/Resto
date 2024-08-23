package com.restaurant.service;

import com.restaurant.model.User;
import com.restaurant.util.HashUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    private final String csvFilePath = "Resto/users.csv";
    private User currentUser;  // Store the currently logged-in user

    public LoginService() {
        // Create the CSV file if it doesn't exist
        File file = new File(csvFilePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Authenticate a user based on username and password, return User if successful
    public User login(String username, String password) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (HashUtil.verifyPassword(password, user.getPasswordHash())) {
                    currentUser = user;  // Set the authenticated user as the current user
                    return user;  // Return the user object if authenticated
                }
            }
        }
        return null;  // Return null if authentication fails
    }

    // Get all users from the CSV file
    private List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip the header
                    continue;
                }
                User user = User.fromCSV(line);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
