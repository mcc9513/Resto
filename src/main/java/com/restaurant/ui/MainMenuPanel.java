package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    // Declare the buttons for navigation
    public JButton menuManagementButton, orderManagementButton, reportButton;
    public JButton inventoryManagementButton, staffManagementButton, tableManagementButton;
    public JButton logoutButton;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Pass the CardLayout and mainPanel to switch between panels
    public MainMenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Set up the layout
        setLayout(new GridLayout(2, 4));  // Two rows and three columns for buttons

        // Initialize buttons
        menuManagementButton = new JButton("Menu Management");
        orderManagementButton = new JButton("Order Management");
        reportButton = new JButton("View Reports");
        inventoryManagementButton = new JButton("Inventory Management");
        staffManagementButton = new JButton("Staff Management");
        tableManagementButton = new JButton("Table Management");
        logoutButton = new JButton("Logout");

        // Add buttons to the panel
        add(menuManagementButton);
        add(orderManagementButton);
        add(reportButton);
        add(inventoryManagementButton);
        add(staffManagementButton);
        add(tableManagementButton);
        add(logoutButton);

        // Add action listeners to buttons to navigate between panels
        menuManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu");
            }
        });

        orderManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Orders");
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Reports");
            }
        });

        inventoryManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Inventory");
            }
        });

        staffManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Staff");
            }
        });

        tableManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Tables");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });
    }
}

