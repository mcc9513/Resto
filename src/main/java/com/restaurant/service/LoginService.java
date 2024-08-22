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

    // Register a new user
    public boolean registerUser(User user) {
        List<User> users = getAllUsers();
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                return false; // Username already exists
            }
        }
        // Hash the user's password before storing it
        String hashedPassword = HashUtil.hashPassword(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);

        // Add the new user to the list
        users.add(user);

        // Save the updated user list to CSV
        return saveAllUsersToCSV(users);
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

    // Get the currently logged-in user
    public User getCurrentUser() {
        return currentUser;
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

    // Save all users to the CSV file
    private boolean saveAllUsersToCSV(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (User user : users) {
                bw.write(user.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
