package com.restaurant.service;

import com.restaurant.model.User;
import com.restaurant.util.HashUtil;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;
    private static final String TEST_CSV_FILE = "test_users.csv";

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        loginService = new LoginService();
        // Use reflection to set the test CSV file
        Field csvFilePathField = LoginService.class.getDeclaredField("csvFilePath");
        csvFilePathField.setAccessible(true);
        csvFilePathField.set(loginService, TEST_CSV_FILE);
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after each test
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

//    @Test
//    void testRegisterUser() {
//        User user = new User(1, "testuser", "password", "Test", "User", 1); // Assuming 1 is for USER role
//        boolean result = loginService.registerUser(user);
//        assertTrue(result);
//
//        // Try to register the same user again
//        result = loginService.registerUser(user);
//        assertFalse(result);
//    }
//
//    @Test
//    void testLogin() {
//        User user = new User(2, "loginuser", "password", "Login", "User", 1);
//        loginService.registerUser(user);
//
//        User loggedInUser = loginService.login("loginuser", "password");
//        assertNotNull(loggedInUser);
//        assertEquals("loginuser", loggedInUser.getUsername());
//
//        // Test with wrong password
//        loggedInUser = loginService.login("loginuser", "wrongpassword");
//        assertNull(loggedInUser);
//
//        // Test with non-existent user
//        loggedInUser = loginService.login("nonexistentuser", "password");
//        assertNull(loggedInUser);
//    }
//
//    @Test
//    void testGetCurrentUser() {
//        User user = new User(3, "currentuser", "password", "Current", "User", 1);
//        loginService.registerUser(user);
//
//        // Login and check current user
//        loginService.login("currentuser", "password");
//        User currentUser = loginService.getCurrentUser();
//        assertNotNull(currentUser);
//        assertEquals("currentuser", currentUser.getUsername());
//    }
//
//    @Test
//    void testPasswordHashing() throws NoSuchFieldException, IllegalAccessException {
//        User user = new User(4, "hashuser", "password", "Hash", "User", 1);
//        loginService.registerUser(user);
//
//        // Use reflection to access private method getAllUsers
//        Field usersField = LoginService.class.getDeclaredField("csvFilePath");
//        usersField.setAccessible(true);
//        String csvFilePath = (String) usersField.get(loginService);
//
//        List<User> users = getAllUsers(csvFilePath);
//        User savedUser = users.stream().filter(u -> u.getUsername().equals("hashuser")).findFirst().orElse(null);
//        assertNotNull(savedUser);
//
//        // Check that the password is hashed
//        assertNotEquals("password", savedUser.getPasswordHash());
//        assertTrue(HashUtil.verifyPassword("password", savedUser.getPasswordHash()));
//    }

    // Helper method to get all users (simulating the private method in LoginService)
    private List<User> getAllUsers(String csvFilePath) {
        // Implementation similar to the private method in LoginService
        // This is just a placeholder, you'd need to implement this method
        return List.of();
    }
}