package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JButton menuManagementButton, orderManagementButton, reportButton;

    public MainMenuPanel() {
        setLayout(new GridLayout(1, 3));  // Simple grid layout

        // Initialize buttons
        menuManagementButton = new JButton("Menu Management");
        orderManagementButton = new JButton("Order Management");
        reportButton = new JButton("View Reports");

        // Add buttons to panel
        add(menuManagementButton);
        add(orderManagementButton);
        add(reportButton);
    }
}

