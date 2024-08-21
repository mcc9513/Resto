package com.restaurant.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.restaurant.model.User;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the LoginService class.
 * It tests various scenarios related to user registration and authentication.
 */
public class LoginServiceTest {

    private LoginService loginService;

    /**
     * This method is run before each test.
     * It sets up a fresh LoginService instance for each test to ensure isolation.
     */
    @BeforeEach
    void setUp() {
        loginService = new LoginService();
    }

    /**
     * Tests a successful login scenario.
     * It registers a user and then attempts to log in with correct credentials.
     */
    @Test
    void testSuccessfulLogin() {
        // Arrange: Create a test user with a password
        User testUser = new User(1, "password123", "JohnD", "staff", 0.0);
        loginService.registerUser(testUser);

        // Act: Attempt to log in with correct credentials
        boolean result = loginService.login(1, "password123");

        // Assert: Verify that the login was successful
        assertTrue(result, "Login should be successful with correct credentials");
    }

    /**
     * Tests a failed login scenario due to incorrect password.
     * It registers a user and then attempts to log in with a wrong password.
     */
    @Test
    void testFailedLogin() {
        // Arrange: Create a test user with a password
        User testUser = new User(1, "password123", "JohnD", "staff", 0.0);
        loginService.registerUser(testUser);

        // Act: Attempt to log in with incorrect password
        boolean result = loginService.login(1, "wrongpassword");

        // Assert: Verify that the login failed
        assertFalse(result, "Login should fail with incorrect password");
    }

    /**
     * Tests a login attempt with a non-existent user ID.
     * It tries to log in with an ID that hasn't been registered.
     */
    @Test
    void testLoginNonexistentUser() {
        // Act: Attempt to log in with a non-existent user ID
        boolean result = loginService.login(999, "password123");

        // Assert: Verify that the login failed
        assertFalse(result, "Login should fail for non-existent user");
    }

    /**
     * Tests the user registration process.
     * It registers a new user and then verifies if the user can log in.
     */
    @Test
    void testRegisterUser() {
        // Arrange: Create a new user
        User newUser = new User(2, "password456", "JaneS", "manager", 0.0);

        // Act: Register the new user
        boolean registrationResult = loginService.registerUser(newUser);

        // Assert: Verify that registration was successful and user can log in
        assertTrue(registrationResult, "User registration should be successful");
        assertTrue(loginService.login(2, "password456"), "Newly registered user should be able to log in");
    }

    /**
     * Tests the scenario of attempting to register a user with an ID that already exists.
     * It registers a user, then tries to register another user with the same ID.
     */
    @Test
    void testRegisterDuplicateUser() {
        // Arrange: Create two users with the same ID
        User user1 = new User(3, "password789", "BobJ", "staff", 0.0);
        User user2 = new User(3, "differentpassword", "BobJ", "staff", 0.0);

        // Act: Register the first user, then attempt to register the second
        boolean firstRegistration = loginService.registerUser(user1);
        boolean secondRegistration = loginService.registerUser(user2);

        // Assert: Verify that the first registration succeeded and the second failed
        assertTrue(firstRegistration, "First user registration should be successful");
        assertFalse(secondRegistration, "Second registration with same ID should fail");
    }
}