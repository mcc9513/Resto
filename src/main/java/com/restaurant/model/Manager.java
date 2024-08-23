package com.restaurant.model;

import java.util.HashSet;

public class Manager extends User {

    private HashSet<String> permissions;

    // Constructor for Manager
    public Manager(int id, String username, String passwordHash, String firstNameLastInitial, double hoursWorked) {
        super(id, username, passwordHash, firstNameLastInitial, "Manager", hoursWorked);  // Role is explicitly set to "Manager"
        this.permissions = new HashSet<>();
        assignAllPermissions();
    }

    // Assign all permissions to the manager
    private void assignAllPermissions() {
        permissions.add("ADD_INVENTORY");
        permissions.add("REMOVE_INVENTORY");
        permissions.add("EDIT_INVENTORY");
        permissions.add("VIEW_INVENTORY");
    }

    // Convert Manager to CSV format (overriding User's toCSV)
    @Override
    public String toCSV() {
        return getId() + "," + getUsername() + "," + getPasswordHash() + "," + getFirstNameLastInitial() + "," + getRole() + "," + getHoursWorked();
    }
}



