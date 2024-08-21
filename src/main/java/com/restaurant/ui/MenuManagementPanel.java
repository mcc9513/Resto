package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class MenuManagementPanel extends JPanel {
    private JTable menuTable;
    private JButton addItemButton, editItemButton, deleteItemButton, mainButton, logOutButton;

    public MenuManagementPanel() {
        setLayout(new BorderLayout());

        // Menu Table | Code for database will go here .
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
        mainButton = new JButton("Home");
        logOutButton = new JButton("Log Out");
        buttonPanel.add(addItemButton);
        buttonPanel.add(editItemButton);
        buttonPanel.add(deleteItemButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(logOutButton);

        // Add components to panel
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
