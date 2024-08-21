package com.restaurant.model;

import java.util.HashSet;

public class Manager extends User {

    private HashSet<String> permissions;

    Manager(int id, String passwordHash, String firstNameLastInitial, String role, double hoursWorked) {
        super(id, passwordHash, firstNameLastInitial, role, hoursWorked);
        this.permissions = new HashSet<>();
        assignAllPermissions();
    }
    private void assignAllPermissions() {
        permissions.add("ADD_INVENTORY");
        permissions.add("REMOVE_INVENTORY");
        permissions.add("EDIT_INVENTORY");
        permissions.add("VIEW_INVENTORY");
    }


}

