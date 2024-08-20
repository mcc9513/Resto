package com.restaurant.model;

public class Manager extends User {
    Manager(int id, String passwordHash, String firstNameLastInitial, String role, double hoursWorked) {
        super(id, passwordHash, firstNameLastInitial, role, hoursWorked);
    }
}

