package com.restaurant.model;

public class User {
    private int id;
    private String passwordHash;
    private String firstNameLastInitial;
    private String role;
    private double hoursWorked;

    public User(int id, String passwordHash, String firstNameLastInitial, String role, double hoursWorked) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.firstNameLastInitial = firstNameLastInitial;
        this.role = role;
        this.hoursWorked = hoursWorked;
    }
    public User() {};

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPassword(String password) {
        this.passwordHash = passwordHash;
    }
    public String getFirstNameLastInitial() {
        return firstNameLastInitial;
    }
    public void setFirstNameLastInitial(String firstNameLastInitial) {
        this.firstNameLastInitial = firstNameLastInitial;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public double getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }



}
