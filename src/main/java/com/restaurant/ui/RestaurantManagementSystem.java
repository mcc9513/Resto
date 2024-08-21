package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class RestaurantManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RestaurantManagementSystem() {
        // Set up the main JFrame
        frame = new JFrame("Restaurant Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create all the panels
        LoginPanel loginPanel = new LoginPanel();
        MainMenuPanel mainMenuPanel = new MainMenuPanel();
        InventoryManagmentPanel inventoryPanel = new InventoryManagmentPanel();
        OrderManagementPanel orderPanel = new OrderManagementPanel();
        MenuManagementPanel menuPanel = new MenuManagementPanel();
        ReportPanel reportPanel = new ReportPanel();
        StaffManagementPanel staffPanel = new StaffManagementPanel();
        TableManagmentPanel tablePanel = new TableManagmentPanel();

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
        loginPanel.loginButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        // Handle navigation buttons in MainMenuPanel to switch between different panels
        mainMenuPanel.menuManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        mainMenuPanel.orderManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Orders"));
        mainMenuPanel.reportButton.addActionListener(e -> cardLayout.show(mainPanel, "Reports"));
        mainMenuPanel.inventoryManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Inventory"));
        mainMenuPanel.staffManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Staff"));
        mainMenuPanel.tableManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Tables"));
    }

    public static void main(String[] args) {
        // Launch the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new RestaurantManagementSystem());
    }
}



