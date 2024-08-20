package com.restaurant.model;

public class Staff extends User {
    Staff(int id, String passwordHash, String firstNameLastInitial, String role, double hoursWorked) {
        super(id, passwordHash, firstNameLastInitial, role, hoursWorked);
    }



}
