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

    // Check if the manager has a specific permission
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    // Convert Manager to CSV format (overriding User's toCSV)
    @Override
    public String toCSV() {
        return getId() + "," + getUsername() + "," + getPasswordHash() + "," + getFirstNameLastInitial() + "," + getRole() + "," + getHoursWorked();
    }

    // Create Manager from CSV (static method)
    public static Manager fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String username = fields[1];
        String passwordHash = fields[2];
        String firstNameLastInitial = fields[3];
        double hoursWorked = Double.parseDouble(fields[5]);

        return new Manager(id, username, passwordHash, firstNameLastInitial, hoursWorked);
    }

    // Getters and Setters for permissions
    public HashSet<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(HashSet<String> permissions) {
        this.permissions = permissions;
    }
}



