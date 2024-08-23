package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;
import com.restaurant.service.InventoryService;
import com.restaurant.service.UserService;
import com.restaurant.service.LoginService;
import com.restaurant.model.User;

public class RestaurantManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private UserService userService;
    private LoginService loginService;

    public RestaurantManagementSystem() {
        // Initialize services
        InventoryService inventoryService = new InventoryService();
        userService = new UserService();
        loginService = new LoginService();

        // Set up the main JFrame
        frame = new JFrame("Restaurant Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create all the panels and pass the necessary services
        LoginPanel loginPanel = new LoginPanel(loginService);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout, mainPanel);
        InventoryManagementPanel inventoryPanel = new InventoryManagementPanel(inventoryService);
        OrderManagementPanel orderPanel = new OrderManagementPanel();
        MenuManagementPanel menuPanel = new MenuManagementPanel();
        ReportPanel reportPanel = new ReportPanel();
        StaffManagementPanel staffPanel = new StaffManagementPanel();
        TableManagementPanel tablePanel = new TableManagementPanel();

        // Add all panels to the main panel (CardLayout)
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(inventoryPanel, "Inventory");
        mainPanel.add(orderPanel, "Orders");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(reportPanel, "Reports");
        mainPanel.add(staffPanel, "Staff");
        mainPanel.add(tablePanel, "Tables");

        // Set default panel to Login
        cardLayout.show(mainPanel, "Login");

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Handle login button action in LoginPanel to navigate to Main Menu
        loginPanel.loginButton.addActionListener(e -> {
            User authenticatedUser = loginService.login(loginPanel.usernameField.getText(), new String(loginPanel.passwordField.getPassword()));
            if (authenticatedUser != null) {
                cardLayout.show(mainPanel, "MainMenu");
                // You might want to pass the authenticated user to the MainMenuPanel or store it somewhere
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Handle navigation buttons in MainMenuPanel to switch between different panels
        mainMenuPanel.menuManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        mainMenuPanel.orderManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Orders"));
        mainMenuPanel.reportButton.addActionListener(e -> cardLayout.show(mainPanel, "Reports"));
        mainMenuPanel.inventoryManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Inventory"));
        mainMenuPanel.staffManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Staff"));
        mainMenuPanel.tableManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Tables"));
        mainMenuPanel.logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
    }

    public static void main(String[] args) {
        // Launch the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new RestaurantManagementSystem());
    }
}