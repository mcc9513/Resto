package com.restaurant.ui;

import com.restaurant.model.MenuItem;
import com.restaurant.service.MenuService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MenuManagementPanel extends JPanel {
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private MenuService menuService;
    private JFrame parentFrame; // Reference to the parent frame

    public MenuManagementPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Store the reference to the parent frame
        // Initialize MenuService for handling CSV operations
        menuService = new MenuService();

        setLayout(new BorderLayout());

        // Create table model with column names
        tableModel = new DefaultTableModel(new String[]{"Name", "Description", "Preparation Time", "Price", "Ingredients"}, 0);
        menuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial data from the Menu.csv file
        loadMenuItems();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addMenuItem());
        buttonPanel.add(addButton);

        // Edit Button
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            try {
                editMenuItem();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(editButton);

        // Delete Button
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteMenuItem());
        buttonPanel.add(deleteButton);

        // Back to Main Menu Button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            // Switch to MainMenuPanel
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new MainMenuPanel(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        buttonPanel.add(backButton);

        // Log Out Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> {
            // Log out the user and return to the login screen
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new LoginPanel(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Load menu items from Menu.csv and display them in the table
    private void loadMenuItems() {
        tableModel.setRowCount(0); // Clear existing rows
        try {
            List<MenuItem> items = menuService.loadMenuItems();
            for (MenuItem item : items) {
                tableModel.addRow(new Object[]{
                        item.getName(),
                        item.getDescription(),
                        item.getPreparationTime(),
                        item.getPrice(),
                        String.join(", ", item.getIngredients())
                });
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading menu items: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add a new menu item
    private void addMenuItem() {
        MenuItem newItem = showMenuItemDialog(null);
        if (newItem != null) {
            try {
                menuService.addMenuItem(newItem);
                loadMenuItems(); // Reload table
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error adding menu item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Edit the selected menu item
    private void editMenuItem() throws IOException {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            MenuItem existingItem = menuService.loadMenuItems().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst().orElse(null);
            MenuItem updatedItem = showMenuItemDialog(existingItem);
            if (updatedItem != null) {
                try {
                    menuService.editMenuItem(name, updatedItem);
                    loadMenuItems(); // Reload table
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error editing menu item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a menu item to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Delete the selected menu item
    private void deleteMenuItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + name + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                try {
                    menuService.deleteMenuItem(name);
                    loadMenuItems(); // Reload table
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error deleting menu item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a menu item to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Show the dialog for adding or editing a menu item
    private MenuItem showMenuItemDialog(MenuItem existingItem) {
        JTextField nameField = new JTextField(existingItem != null ? existingItem.getName() : "");
        JTextField descriptionField = new JTextField(existingItem != null ? existingItem.getDescription() : "");
        JTextField preparationTimeField = new JTextField(existingItem != null ? String.valueOf(existingItem.getPreparationTime()) : "");
        JTextField priceField = new JTextField(existingItem != null ? String.valueOf(existingItem.getPrice()) : "");
        JTextField ingredientsField = new JTextField(existingItem != null ? String.join(", ", existingItem.getIngredients()) : "");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Preparation Time (min):"));
        panel.add(preparationTimeField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Ingredients (comma separated):"));
        panel.add(ingredientsField);

        int result = JOptionPane.showConfirmDialog(this, panel, existingItem == null ? "Add Menu Item" : "Edit Menu Item",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();
            int preparationTime = Integer.parseInt(preparationTimeField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            List<String> ingredients = List.of(ingredientsField.getText().trim().split("\\s*,\\s*"));

            return new MenuItem(name, description, preparationTime, price, ingredients);
        } else {
            return null; // User cancelled the operation
        }
    }
}
