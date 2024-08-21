package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class InventoryManagmentPanel extends JPanel {
    private JTable inventoryTable;
    private JButton addButton, updateButton, deleteButton, mainButton, logOutButton;

    public InventoryManagmentPanel() {
        setLayout(new BorderLayout());

        // Inventory Table | Code for the database will go here.
        String[] columns = {"Item Name", "Quantity", "Price"};
        Object[][] data = {
                {"Apples", 100, "$1.00"},
                {"Bananas", 50, "$0.50"}
        };
        inventoryTable = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(inventoryTable);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Item");
        updateButton = new JButton("Update Item");
        deleteButton = new JButton("Delete Item");
        mainButton = new JButton("Home");
        logOutButton = new JButton("Log Out");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(logOutButton);

        // Add components to panel
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
