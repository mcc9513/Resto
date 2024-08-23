package com.restaurant.model;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String firstNameLastInitial;
    private String role;
    private double hoursWorked;

    // Constructor with all fields
    public User(int id, String username, String passwordHash, String firstNameLastInitial, String role, double hoursWorked) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstNameLastInitial = firstNameLastInitial;
        this.role = role;
        this.hoursWorked = hoursWorked;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {  // Renamed this method
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {  // Updated setter method
        this.passwordHash = passwordHash;
    }

    public String getFirstNameLastInitial() {
        return firstNameLastInitial;
    }


    public String getRole() {
        return role;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    // Convert User to CSV format
    public String toCSV() {
        return id + "," + username + "," + passwordHash + "," + firstNameLastInitial + "," + role + "," + hoursWorked;
    }

    // Create User from CSV
    public static User fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String username = fields[1];
        String passwordHash = fields[2];
        String firstNameLastInitial = fields[3];
        String role = fields[4];
        double hoursWorked = Double.parseDouble(fields[5]);

        return new User(id, username, passwordHash, firstNameLastInitial, role, hoursWorked);
    }
}

