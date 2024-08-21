package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    // Declare the buttons for navigation
    public JButton menuManagementButton, orderManagementButton, reportButton;
    public JButton inventoryManagementButton, staffManagementButton, tableManagementButton;

    public MainMenuPanel() {
        // Set up the layout
        setLayout(new GridLayout(2, 3));  // Two rows and three columns for buttons

        // Initialize buttons
        menuManagementButton = new JButton("Menu Management");
        orderManagementButton = new JButton("Order Management");
        reportButton = new JButton("View Reports");
        inventoryManagementButton = new JButton("Inventory Management");
        staffManagementButton = new JButton("Staff Management");
        tableManagementButton = new JButton("Table Management");

        // Add buttons to the panel
        add(menuManagementButton);
        add(orderManagementButton);
        add(reportButton);
        add(inventoryManagementButton);
        add(staffManagementButton);
        add(tableManagementButton);
    }
}
