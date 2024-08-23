package com.restaurant.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Initialize the User object with example data
        user = new User(1, "user1", "hashedPassword", "John D", "Staff", 40.0);
    }

    // Test the constructor and getters
    @Test
    void testConstructorAndGetters() {
        assertEquals(1, user.getId(), "User ID should match the initialized value.");
        assertEquals("user1", user.getUsername(), "Username should match the initialized value.");
        assertEquals("hashedPassword", user.getPasswordHash(), "Password hash should match the initialized value.");
        assertEquals("John D", user.getFirstNameLastInitial(), "Name should match the initialized value.");
        assertEquals("Staff", user.getRole(), "Role should match the initialized value.");
        assertEquals(40.0, user.getHoursWorked(), 0.01, "Hours worked should match the initialized value.");
    }

    // Test setting and getting the password hash
    @Test
    void testSetPasswordHash() {
        user.setPasswordHash("newHashedPassword");
        assertEquals("newHashedPassword", user.getPasswordHash(), "Password hash should be updated to the new value.");
    }

    // Test the CSV conversion
    @Test
    void testToCSV() {
        String csv = user.toCSV();
        assertEquals("1,user1,hashedPassword,John D,Staff,40.0", csv, "CSV format should match the expected string.");
    }

    // Test creating a User object from a CSV string
    @Test
    void testFromCSV() {
        String csvLine = "2,user2,hashedPassword2,Jane D,Manager,50.0";
        User newUser = User.fromCSV(csvLine);

        assertEquals(2, newUser.getId(), "User ID should match the value from the CSV.");
        assertEquals("user2", newUser.getUsername(), "Username should match the value from the CSV.");
        assertEquals("hashedPassword2", newUser.getPasswordHash(), "Password hash should match the value from the CSV.");
        assertEquals("Jane D", newUser.getFirstNameLastInitial(), "Name should match the value from the CSV.");
        assertEquals("Manager", newUser.getRole(), "Role should match the value from the CSV.");
        assertEquals(50.0, newUser.getHoursWorked(), 0.01, "Hours worked should match the value from the CSV.");
    }

}

