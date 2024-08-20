package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JLabel statusLabel;

    public LoginPanel() {
        setLayout(new GridLayout(3, 2));

        // Initialize components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        statusLabel = new JLabel("Please enter your credentials.");

        // Add components
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(resetButton);

        // Add status label
        add(statusLabel, BorderLayout.SOUTH);
    }
}

