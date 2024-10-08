package com.restaurant.ui;

import com.restaurant.model.User;
import com.restaurant.service.*;

import javax.swing.*;
import java.awt.*;

public class RestaurantManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginService loginService;
    private InventoryService inventoryService;
    private UserService userService;
    private TableService tableService;
    private OrderService orderService;  // Add OrderService
    private MenuService menuService;
    private RestaurantController controller;
    private User currentUser;

    public RestaurantManagementSystem() {
        // Initialize services
        inventoryService = new InventoryService();
        loginService = new LoginService();
        userService = new UserService();
        tableService = new TableService();
        orderService = new OrderService();  // Initialize OrderService
        MenuService menuService = new MenuService();  // Initialize MenuService
        ReportService reportService = new ReportService(orderService, inventoryService);

        controller = new RestaurantController();

        // Set up the main JFrame
        frame = new JFrame("Restaurant Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create all the panels and pass JFrame to those that require it
        LoginPanel loginPanel = new LoginPanel(frame, loginService);  // Pass JFrame and LoginService
        MainMenuPanel mainMenuPanel = new MainMenuPanel(frame, cardLayout, mainPanel, currentUser, inventoryService); // Pass JFrame
        InventoryManagementPanel inventoryPanel = new InventoryManagementPanel(inventoryService);
        OrderManagementPanel orderPanel = new OrderManagementPanel(orderService, menuService, cardLayout, mainPanel); // Pass OrderService and MenuService to OrderManagementPanel
        MenuManagementPanel menuPanel = new MenuManagementPanel(frame, cardLayout, mainPanel, currentUser);
        ReportPanel reportPanel = new ReportPanel(reportService, inventoryService, orderService, menuService, cardLayout, mainPanel);
        StaffManagementPanel staffPanel = new StaffManagementPanel(userService, cardLayout, mainPanel); // Pass UserService, CardLayout, and MainPanel
        TableManagementPanel tablePanel = new TableManagementPanel(tableService, cardLayout, mainPanel); // Pass TableService
        RestaurantController controller = new RestaurantController();

        // Add all panels to the main panel (CardLayout)
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(inventoryPanel, "Inventory");
        mainPanel.add(orderPanel, "Orders");  // Add OrderManagementPanel here
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
            String username = loginPanel.usernameField.getText();
            String password = new String(loginPanel.passwordField.getPassword());
            currentUser = loginService.login(username, password);

            if (currentUser != null) {
                // Update MainMenuPanel with the authenticated user
                MainMenuPanel newMainMenuPanel = new MainMenuPanel(frame, cardLayout, mainPanel, currentUser, inventoryService);
                mainPanel.add(newMainMenuPanel, "MainMenu");

                // Show the main menu panel after successful login
                cardLayout.show(mainPanel, "MainMenu");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        // Launch the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(RestaurantManagementSystem::new);
    }
}



