package com.restaurant.ui;

import com.restaurant.model.InventoryItem;
import com.restaurant.service.InventoryService;
import com.restaurant.service.RestaurantController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventoryManagementPanel extends JPanel {

    private InventoryService inventoryService;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private RestaurantController controller;

    public InventoryManagementPanel(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.controller = controller;
        setLayout(new BorderLayout());

        // Create table for inventory
        String[] columnNames = {"Item ID", "Item Name", "Quantity", "Threshold", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        // Add table to the center of the panel
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Item");
        JButton editButton = new JButton("Edit Item");
        JButton deleteButton = new JButton("Delete Item");
        JButton backButton = new JButton("Back to Main Menu");
        JButton logoutButton = new JButton("Logout");
        JButton reloadButton = new JButton("Reload Inventory");

        // Add buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(reloadButton);

        // Add button panel to the bottom of the panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Set custom renderer to highlight rows with quantity < threshold
        inventoryTable.setDefaultRenderer(Object.class, new CustomRenderer());

        // Load data into the table
        loadInventoryData();

        // Add action listeners for buttons
        addButton.addActionListener(e -> addItem());
        editButton.addActionListener(e -> editItem());
        deleteButton.addActionListener(e -> deleteItem());
        backButton.addActionListener(e -> returnToMainMenu());
        logoutButton.addActionListener(e -> logout());
        reloadButton.addActionListener(e -> loadInventoryData());
    }

    // Load data from CSV into the table
    public void loadInventoryData() {
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        tableModel.setRowCount(0); // Clear existing data
        for (InventoryItem item : items) {
            Object[] rowData = {item.getItemId(), item.getItemName(), item.getQuantity(), item.getThreshold(), item.getPrice()};
            tableModel.addRow(rowData);
        }
    }

    // Custom cell renderer to highlight rows with quantity less than threshold
    private class CustomRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int quantity = (int) table.getValueAt(row, 2);  // Quantity column index
            int threshold = (int) table.getValueAt(row, 3); // Threshold column index

            // Highlight row in yellow if quantity is less than threshold
            if (quantity < threshold) {
                c.setBackground(Color.YELLOW);
            } else {
                c.setBackground(Color.WHITE); // Reset background for other rows
            }

            return c;
        }
    }

    // Add new item method
    void addItem() {
        String itemName = JOptionPane.showInputDialog("Enter item name:");
        String quantityStr = JOptionPane.showInputDialog("Enter quantity:");
        String thresholdStr = JOptionPane.showInputDialog("Enter threshold:");
        String priceStr = JOptionPane.showInputDialog("Enter price:");

        try {
            int quantity = Integer.parseInt(quantityStr);
            int threshold = Integer.parseInt(thresholdStr);
            double price = Double.parseDouble(priceStr);
            InventoryItem newItem = new InventoryItem(itemName, quantity, threshold, price);
            inventoryService.addInventoryItem(newItem);
            loadInventoryData();  // Reload the table data
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    // Edit selected item method
    private void editItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow >= 0) {
            int itemId = (int) inventoryTable.getValueAt(selectedRow, 0);
            String itemName = (String) inventoryTable.getValueAt(selectedRow, 1);
            String quantityStr = JOptionPane.showInputDialog("Enter new quantity:", inventoryTable.getValueAt(selectedRow, 2));
            String thresholdStr = JOptionPane.showInputDialog("Enter new threshold:", inventoryTable.getValueAt(selectedRow, 3));
            String priceStr = JOptionPane.showInputDialog("Enter new price:", inventoryTable.getValueAt(selectedRow, 4));

            try {
                int quantity = Integer.parseInt(quantityStr);
                int threshold = Integer.parseInt(thresholdStr);
                double price = Double.parseDouble(priceStr);

                InventoryItem updatedItem = new InventoryItem(itemId, itemName, quantity, threshold, price);
                inventoryService.updateInventoryItem(updatedItem);
                loadInventoryData();  // Reload the table data
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to edit.");
        }
    }

    // Delete selected item method
    void deleteItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow >= 0) {
            int itemId = (int) inventoryTable.getValueAt(selectedRow, 0);
            inventoryService.removeInventoryItem(itemId);
            loadInventoryData();  // Reload the table data
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.");
        }
    }

    // Go back to main menu method
    private void returnToMainMenu() {
        // Implement navigation to the main menu (CardLayout-based)
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "MainMenu");
    }

    // Logout method
    private void logout() {
        // Implement logout logic here
        CardLayout cl = (CardLayout) getParent().getLayout();
        cl.show(getParent(), "Login");
    }
}





