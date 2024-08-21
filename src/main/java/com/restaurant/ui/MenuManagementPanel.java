package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class MenuManagementPanel extends JPanel {
    private JTable menuTable;
    private JButton addItemButton, editItemButton, deleteItemButton;

    public MenuManagementPanel() {
        setLayout(new BorderLayout());

        // Menu Table
        String[] columns = {"Item Name", "Category", "Price"};
        Object[][] data = {
                {"Burger", "Main Course", "$8.00"},
                {"Salad", "Appetizer", "$5.00"}
        };
        menuTable = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(menuTable);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addItemButton = new JButton("Add Item");
        editItemButton = new JButton("Edit Item");
        deleteItemButton = new JButton("Delete Item");
        buttonPanel.add(addItemButton);
        buttonPanel.add(editItemButton);
        buttonPanel.add(deleteItemButton);

        // Add components to panel
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
