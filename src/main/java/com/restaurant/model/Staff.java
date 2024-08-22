package com.restaurant.model;

public class Staff {
    private int id;
    private String name;
    private String role;
    private double hoursWorked;

    // Constructor
    public Staff(int id, String name, String role, double hoursWorked) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.hoursWorked = hoursWorked;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // Convert Staff to CSV format
    public String toCSV() {
        return id + "," + name + "," + role + "," + hoursWorked;
    }

    // Create Staff from CSV
    public static Staff fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String role = fields[2];
        double hoursWorked = Double.parseDouble(fields[3]);

        return new Staff(id, name, role, hoursWorked);
    }
}