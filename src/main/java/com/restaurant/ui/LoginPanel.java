package com.restaurant.ui;

import com.restaurant.model.User;
import com.restaurant.service.LoginService;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton, resetButton;
    private JLabel statusLabel;
    private LoginService loginService;

    public LoginPanel(JFrame frame, LoginService loginService) {
        this.loginService = loginService; // Inject LoginService

        // Set the layout to GridBagLayout for flexibility in positioning components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize components
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        statusLabel = new JLabel("Please enter your credentials.");

        // Layout setup for username, password, and buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // Buttons (Login and Reset)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resetButton, gbc);

        // Status label at the bottom
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(statusLabel, gbc);

        // Set up action listeners
        loginButton.addActionListener(e -> handleLogin());
        resetButton.addActionListener(e -> resetFields());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check with LoginService
        User authenticatedUser = loginService.login(username, password);
        if (authenticatedUser != null) { // User authenticated
            statusLabel.setText("Login successful!");
            // Trigger navigation to the main menu or next panel
            // This can be done by calling a method in the parent window that handles the panel switching
        } else { // Invalid credentials
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void resetFields() {
        usernameField.setText("");
        passwordField.setText("");
        statusLabel.setText("Please enter your credentials.");
    }
}
