package com.restaurant.service;

import com.restaurant.model.User;
import com.restaurant.util.HashUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final String csvFilePath = "Resto/users.csv";

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
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                return false; // Username already exists
            }
        }
        user.setPasswordHash(HashUtil.hashPassword(user.getPasswordHash()));  // Hash the password
        users.add(user);
        return saveAllUsersToCSV(users);
    }

    // Update an existing user
    public boolean updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);  // Update the user at the correct index
                return saveAllUsersToCSV(users);
            }
        }
        return false;
    }

    // Remove a user by ID
    public boolean removeUser(int userId) {
        List<User> users = getAllUsers();
        users.removeIf(user -> user.getId() == userId);
        return saveAllUsersToCSV(users);
    }

    // Get all users from the CSV file
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header
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
            bw.write("id,username,passwordHash,firstNameLastInitial,role,hoursWorked");
            bw.newLine();
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



