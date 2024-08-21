package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginPanel extends JPanel {
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton, resetButton;
    private JLabel statusLabel, logoLabel;

    public LoginPanel() {
        // Set the layout to GridBagLayout for flexibility in positioning components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize components
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");
        statusLabel = new JLabel("Please enter your credentials.");

        // Load the image from the resources folder and resize it
        URL imageUrl = getClass().getClassLoader().getResource("restologo.png");
        if (imageUrl != null) {
            ImageIcon logoIcon = new ImageIcon(imageUrl);

            // Resize the image to fit the label (200x200 pixels as an example)
            Image logoImage = logoIcon.getImage();  // Get the original image
            Image resizedImage = logoImage.getScaledInstance(320, 320, Image.SCALE_SMOOTH);  // Resize the image
            logoIcon = new ImageIcon(resizedImage);  // Create a new ImageIcon with the resized image

            logoLabel = new JLabel(logoIcon);
        } else {
            logoLabel = new JLabel("Logo not found!");  // Fallback if the image isn't found
            System.out.println("Image not found at specified path!");
        }

        // Define layout constraints and add components

        // Add logo at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span the logo across 2 columns
        gbc.insets = new Insets(10, 10, 20, 10);  // Extra padding below the logo
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // Username label and text field
        gbc.gridwidth = 1;  // Reset the grid width for other components
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding
        gbc.anchor = GridBagConstraints.EAST;  // Align label to the right
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;  // Align text field to the left
        add(usernameField, gbc);

        // Password label and text field
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
        gbc.gridwidth = 1;  // Reset grid width to 1 for buttons
        gbc.anchor = GridBagConstraints.CENTER;  // Center align the buttons
        gbc.insets = new Insets(20, 10, 10, 10);  // Add more space before buttons
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resetButton, gbc);

        // Status label at the bottom
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // Spanning the status label across 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(statusLabel, gbc);
    }
}


