package com.restaurant.ui;

import com.restaurant.model.User;
import com.restaurant.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginPanelTest {

    private LoginPanel loginPanel;
    private LoginService loginService;
    private JFrame mockFrame;

    @BeforeEach
    void setUp() {
        // Mock the LoginService
        loginService = mock(LoginService.class);

        // Mock the JFrame (parent frame)
        mockFrame = mock(JFrame.class);

        // Initialize the LoginPanel with mocked dependencies
        loginPanel = new LoginPanel(mockFrame, loginService);
    }

    // Test successful login
    @Test
    void testHandleLoginSuccess() {
        // Mock a valid user to be returned on successful login
        User mockUser = new User(1, "testuser", "passwordHash", "Test U", "Staff", 40);
        when(loginService.login("testuser", "password")).thenReturn(mockUser);

        // Simulate entering login credentials
        loginPanel.usernameField.setText("testuser");
        loginPanel.passwordField.setText("password");

        // Trigger the login action
        loginPanel.loginButton.doClick();

        // Verify that the status label is updated to reflect a successful login
        assertEquals("Login successful!", loginPanel.statusLabel.getText(), "Status label should show a successful login message.");

        // Verify that the login service was called with the correct credentials
        verify(loginService, times(1)).login("testuser", "password");
    }

    // Test failed login
    @Test
    void testHandleLoginFail() {
        // Mock a failed login attempt
        when(loginService.login("testuser", "wrongpassword")).thenReturn(null);

        // Simulate entering incorrect login credentials
        loginPanel.usernameField.setText("testuser");
        loginPanel.passwordField.setText("wrongpassword");

        // Trigger the login action
        loginPanel.loginButton.doClick();

        // Verify that the status label is updated to reflect a failed login
        assertEquals("Invalid username or password.", loginPanel.statusLabel.getText(), "Status label should show an invalid login message.");

        // Verify that the login service was called with the incorrect credentials
        verify(loginService, times(1)).login("testuser", "wrongpassword");
    }

    // Test resetting the fields
    @Test
    void testResetFields() {
        // Simulate entering some credentials
        loginPanel.usernameField.setText("testuser");
        loginPanel.passwordField.setText("password");

        // Trigger the reset action
        loginPanel.resetButton.doClick();

        // Verify that the username and password fields are cleared
        assertEquals("", loginPanel.usernameField.getText(), "Username field should be cleared.");
        assertEquals("", new String(loginPanel.passwordField.getPassword()), "Password field should be cleared.");

        // Verify that the status label is reset
        assertEquals("Please enter your credentials.", loginPanel.statusLabel.getText(), "Status label should be reset.");
    }
}
