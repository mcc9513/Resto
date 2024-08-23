package com.restaurant.ui;

import com.restaurant.model.User;
import com.restaurant.service.InventoryService;

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
    private User currentUser;  // Track the current logged-in user

    // Pass the JFrame, CardLayout, mainPanel, and currentUser to switch between panels and check user roles
    public MainMenuPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User currentUser, InventoryService inventoryService) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.currentUser = currentUser;  // Track the current user

        // Set up the layout with 2 rows and 5 columns
        setLayout(new GridLayout(2, 5));  // Two rows and five columns for buttons and the image

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

        // Load the logo image from the resources folder
        ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("restologo.png")); // Load the image
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);  // Center the logo

        // Add the logo after the logout button on the same row
        add(logoLabel);

        // Add action listeners to buttons to navigate between panels
        menuManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Manager".equals(currentUser.getRole())) {
                    cardLayout.show(mainPanel, "Staff");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Access Denied: Only Managers can access this panel.", "Access Restricted", JOptionPane.WARNING_MESSAGE);
                }
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
                if ("Manager".equals(currentUser.getRole())) {
                    cardLayout.show(mainPanel, "Staff");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Access Denied: Only Managers can access this panel.", "Access Restricted", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        inventoryManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Manager".equals(currentUser.getRole())) {
                    cardLayout.show(mainPanel, "Staff");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Access Denied: Only Managers can access this panel.", "Access Restricted", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        staffManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the current user has the role "Manager"
                if ("Manager".equals(currentUser.getRole())) {
                    cardLayout.show(mainPanel, "Staff");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Access Denied: Only Managers can access this panel.", "Access Restricted", JOptionPane.WARNING_MESSAGE);
                }
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
