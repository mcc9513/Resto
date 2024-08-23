package com.restaurant.ui;

import com.restaurant.service.InventoryService;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.ReportService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportPanel extends JPanel {
    private InventoryService inventoryService;
    private OrderService orderService;
    private MenuService menuService;
    private ReportService reportService;

    // Labels to display the report data
    private JLabel totalOrdersLabel;
    private JLabel totalInventoryCountLabel;
    private JLabel totalInventoryValueLabel;
    private JLabel totalSalesValueLabel;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ReportPanel(ReportService reportService, InventoryService inventoryService, OrderService orderService, MenuService menuService, CardLayout cardLayout, JPanel mainPanel) {
        this.reportService = reportService;
        this.inventoryService = inventoryService;
        this.orderService = orderService;
        this.menuService = menuService;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout()); // Use BorderLayout to position components

        // Create a panel for the report information
        JPanel reportPanel = new JPanel(new GridBagLayout());
        reportPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between labels

        // Initialize labels to show the report data
        totalOrdersLabel = new JLabel("Total Orders Placed: ");
        totalInventoryCountLabel = new JLabel("Total Inventory Count: ");
        totalInventoryValueLabel = new JLabel("Total Inventory Value: ");
        totalSalesValueLabel = new JLabel("Total Sales Value: ");

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        totalOrdersLabel.setFont(labelFont);
        totalInventoryCountLabel.setFont(labelFont);
        totalInventoryValueLabel.setFont(labelFont);
        totalSalesValueLabel.setFont(labelFont);

        // Add labels to the report panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        reportPanel.add(totalOrdersLabel, gbc);

        gbc.gridy++;
        reportPanel.add(totalInventoryCountLabel, gbc);

        gbc.gridy++;
        reportPanel.add(totalInventoryValueLabel, gbc);

        gbc.gridy++;
        reportPanel.add(totalSalesValueLabel, gbc);

        // Add the report panel to the center of the main panel
        add(reportPanel, BorderLayout.CENTER);

        // Create a button panel for actions (Refresh, Back, Export, Log Out)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Button to refresh the report data
        JButton refreshButton = new JButton("Refresh Report");
        refreshButton.addActionListener(e -> refreshReport());
        buttonPanel.add(refreshButton);

        // Export report to file button
        JButton exportButton = new JButton("Export Report to File");
        exportButton.addActionListener(e -> exportReportToFile());
        buttonPanel.add(exportButton);

        // Back to Main Menu button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
        buttonPanel.add(backButton);

        // Log Out button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        buttonPanel.add(logoutButton);

        // Add the button panel to the bottom of the main panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Load initial report data
        refreshReport();
    }

    // Refresh the report data by fetching it from the services
    private void refreshReport() {
        int totalOrders = orderService.getAllOrders().size(); // Fetch total orders from OrderService
        int totalInventoryCount = inventoryService.getAllInventoryItems().size(); // Fetch total inventory count
        double totalInventoryValue = inventoryService.getAllInventoryItems().stream()  // Calculate total inventory value
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // Pass the `menuService` as a parameter to `getTotalPrice`
        double totalSalesValue = orderService.getAllOrders().stream()  // Calculate total sales value
                .mapToDouble(order -> order.getTotalPrice(menuService))
                .sum();

        // Update the labels with the fetched data
        totalOrdersLabel.setText("Total Orders Placed: " + totalOrders);
        totalInventoryCountLabel.setText("Total Inventory Count: " + totalInventoryCount);
        totalInventoryValueLabel.setText("Total Inventory Value: $" + totalInventoryValue);
        totalSalesValueLabel.setText("Total Sales Value: $" + totalSalesValue);
    }

    // Method to export the report to a file
    private void exportReportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write("Restaurant Sales Report\n");
            writer.write("-------------------------------\n");
            writer.write(totalOrdersLabel.getText() + "\n");
            writer.write(totalInventoryCountLabel.getText() + "\n");
            writer.write(totalInventoryValueLabel.getText() + "\n");
            writer.write(totalSalesValueLabel.getText() + "\n");
            writer.write("-------------------------------\n");
            JOptionPane.showMessageDialog(this, "Report exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to export report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
