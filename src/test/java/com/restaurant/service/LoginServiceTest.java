package com.restaurant.service;

import com.restaurant.model.User;
import com.restaurant.util.HashUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;
    private static final String TEST_CSV_FILE = "Resto/test_users.csv";

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialize the LoginService and set the CSV file path to a test file
        loginService = new LoginService();

        // Use reflection to set the test CSV file path
        Field csvFilePathField = LoginService.class.getDeclaredField("csvFilePath");
        csvFilePathField.setAccessible(true);
        csvFilePathField.set(loginService, TEST_CSV_FILE);

        // Create test users in the CSV file
        createTestUsers();
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after each test
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    // Create test users in the CSV file for testing purposes
    private void createTestUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_CSV_FILE))) {
            // Write CSV header
            bw.write("id,username,passwordHash,firstNameLastInitial,role,hoursWorked");
            bw.newLine();

            // Initialize the HashUtil object
            HashUtil hashUtil = new HashUtil();

            // Write test users with hashed passwords
            bw.write("1,testuser," + hashUtil.hashPassword("password") + ",Test U,Staff,40");
            bw.newLine();
            bw.write("2,manageruser," + hashUtil.hashPassword("managerpass") + ",Manager M,Manager,50");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Test successful login with correct credentials
    @Test
    void testLoginSuccess() {
        User user = loginService.login("testuser", "password");
        assertNotNull(user, "User should be successfully authenticated.");
        assertEquals("testuser", user.getUsername(), "The username should match the logged-in user.");
    }

    // Test failed login with incorrect password
    @Test
    void testLoginFailIncorrectPassword() {
        User user = loginService.login("testuser", "wrongpassword");
        assertNull(user, "User should not be authenticated with incorrect password.");
    }

    // Test failed login with non-existent username
    @Test
    void testLoginFailNonExistentUser() {
        User user = loginService.login("nonexistentuser", "password");
        assertNull(user, "User should not be authenticated if the username doesn't exist.");
    }

    // Test if the correct user is retrieved after login
    @Test
    void testLoginRetrieveCorrectUser() {
        User user = loginService.login("manageruser", "managerpass");
        assertNotNull(user, "User should be successfully authenticated.");
        assertEquals("manageruser", user.getUsername(), "The correct user should be logged in.");
        assertEquals("Manager", user.getRole(), "The role should be Manager for this user.");
    }

    // Test to check if currentUser is set after successful login
    @Test
    void testCurrentUserSetAfterLogin() throws NoSuchFieldException, IllegalAccessException {
        loginService.login("testuser", "password");

        // Use reflection to access the private currentUser field
        Field currentUserField = LoginService.class.getDeclaredField("currentUser");
        currentUserField.setAccessible(true);
        User currentUser = (User) currentUserField.get(loginService);

        assertNotNull(currentUser, "Current user should be set after login.");
        assertEquals("testuser", currentUser.getUsername(), "Current user should be 'testuser'.");
    }
}
