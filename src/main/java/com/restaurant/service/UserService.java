package com.restaurant.service;

import com.restaurant.model.User;
import com.restaurant.util.HashUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final String csvFilePath = "users.csv";

    public UserService() {
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
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                System.out.println("User already exists");
                return false;
            }
        }

        user.setPasswordHash(HashUtil.hashPassword(user.getPasswordHash()));  // Hash password before saving
        users.add(user);
        return saveAllUsersToCSV(users);
    }

    // Login user by checking the hashed password
    public boolean loginUser(String username, String password) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // Verify the password by comparing the hashed password stored with the hash of input
                return HashUtil.verifyPassword(password, user.getPasswordHash());
            }
        }
        return false;
    }

    // Validate login for login panel
    public boolean validateLogin(String username, String password) {
        return loginUser(username, password);
    }

    // Get all users from CSV
    private List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                User user = User.fromCSV(line);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Save the list of users back to CSV
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


