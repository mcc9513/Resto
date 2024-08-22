package com.restaurant.gui;

import com.restaurant.model.MenuItem;
import com.restaurant.service.MenuService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MenuManagementPanel extends JPanel {
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private MenuService menuService;

    public MenuManagementPanel() {
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuItem();
            }
        });
        buttonPanel.add(addButton);

        // Edit Button
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMenuItem();
            }
        });
        buttonPanel.add(editButton);

        // Delete Button
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMenuItem();
            }
        });
        buttonPanel.add(deleteButton);

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
        MenuItemDialog dialog = new MenuItemDialog(null);
        dialog.setVisible(true);
        MenuItem newItem = dialog.getMenuItem();
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
    private void editMenuItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            MenuItemDialog dialog = new MenuItemDialog(menuService.loadMenuItems().stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst().orElse(null));
            dialog.setVisible(true);
            MenuItem updatedItem = dialog.getMenuItem();
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
}
