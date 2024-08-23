package com.restaurant.ui;

import com.restaurant.model.User;
import com.restaurant.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.mockito.Mockito.*;

class MainMenuPanelTest {

    private MainMenuPanel mainMenuPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User managerUser;
    private User staffUser;
    private InventoryService inventoryService;
    private JFrame mockFrame;

    @BeforeEach
    void setUp() {
        // Mock necessary components
        cardLayout = mock(CardLayout.class);
        mainPanel = mock(JPanel.class);
        inventoryService = mock(InventoryService.class);
        mockFrame = mock(JFrame.class);

        // Set the layout for mainPanel to be a CardLayout
        when(mainPanel.getLayout()).thenReturn(cardLayout);

        // Mock users
        managerUser = new User(1, "manager", "passwordHash", "Manager", "Manager", 40);
        staffUser = new User(2, "staff", "passwordHash", "Staff", "Staff", 40);

        // Initialize MainMenuPanel with the manager user
        mainMenuPanel = new MainMenuPanel(mockFrame, cardLayout, mainPanel, managerUser, inventoryService);
    }

    // Test navigation to Menu Management
    @Test
    void testMenuManagementButtonNavigation() {
        // Trigger the button click
        mainMenuPanel.menuManagementButton.doClick();

        // Verify that the panel switches to the "Menu" panel
        verify(cardLayout, times(1)).show(mainPanel, "Menu");
    }

    // Test navigation to Order Management
    @Test
    void testOrderManagementButtonNavigation() {
        // Trigger the button click
        mainMenuPanel.orderManagementButton.doClick();

        // Verify that the panel switches to the "Orders" panel
        verify(cardLayout, times(1)).show(mainPanel, "Orders");
    }

    // Test navigation to Reports
    @Test
    void testReportButtonNavigation() {
        // Trigger the button click
        mainMenuPanel.reportButton.doClick();

        // Verify that the panel switches to the "Reports" panel
        verify(cardLayout, times(1)).show(mainPanel, "Reports");
    }

    // Test navigation to Inventory Management
    @Test
    void testInventoryManagementButtonNavigation() {
        // Trigger the button click
        mainMenuPanel.inventoryManagementButton.doClick();

        // Verify that the panel switches to the "Inventory" panel
        verify(cardLayout, times(1)).show(mainPanel, "Inventory");
    }

    // Test navigation to Table Management
    @Test
    void testTableManagementButtonNavigation() {
        // Trigger the button click
        mainMenuPanel.tableManagementButton.doClick();

        // Verify that the panel switches to the "Tables" panel
        verify(cardLayout, times(1)).show(mainPanel, "Tables");
    }

    // Test navigation to Staff Management for Manager role
    @Test
    void testStaffManagementButtonForManager() {
        // Trigger the button click
        mainMenuPanel.staffManagementButton.doClick();

        // Verify that the panel switches to the "Staff" panel
        verify(cardLayout, times(1)).show(mainPanel, "Staff");
    }

    // Test logout functionality
    @Test
    void testLogoutButton() {
        // Trigger the button click
        mainMenuPanel.logoutButton.doClick();

        // Verify that the panel switches to the "Login" panel
        verify(cardLayout, times(1)).show(mainPanel, "Login");
    }
}
