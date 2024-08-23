package com.restaurant.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class ManagerTest {

    private Manager manager;

    @BeforeEach
    void setUp() {
        // Initialize the manager with some example data
        manager = new Manager(1, "manager1", "hashedpassword", "John D", 40.0);
    }

    // Test the CSV conversion for Manager
    @Test
    void testToCSV() {
        String csv = manager.toCSV();
        assertEquals("1,manager1,hashedpassword,John D,Manager,40.0", csv, "CSV format should match the expected string.");
    }

    // Test the inheritance of the Manager class from User
    @Test
    void testManagerInheritsFromUser() {
        // Check inherited properties
        assertEquals(1, manager.getId());
        assertEquals("manager1", manager.getUsername());
        assertEquals("hashedpassword", manager.getPasswordHash());
        assertEquals("John D", manager.getFirstNameLastInitial());
        assertEquals("Manager", manager.getRole());  // Explicitly set in Manager
        assertEquals(40.0, manager.getHoursWorked(), 0.1);
    }
}
