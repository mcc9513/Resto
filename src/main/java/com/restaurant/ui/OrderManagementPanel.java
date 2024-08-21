package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class OrderManagementPanel extends JPanel {
    private JTable orderTable;
    private JButton addOrderButton, viewOrderButton, mainButton, logOutButton;

    public OrderManagementPanel() {
        setLayout(new BorderLayout());

        // Order Table
        String[] columns = {"Order ID", "Table No.", "Total Price", "Status"};
        Object[][] data = {
                {"001", "12", "$50.00", "Completed"},
                {"002", "5", "$30.00", "Pending"}
        };
        orderTable = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(orderTable);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addOrderButton = new JButton("Add Order");
        viewOrderButton = new JButton("View Order");
        mainButton = new JButton("Home");
        logOutButton = new JButton("Log Out");
        buttonPanel.add(addOrderButton);
        buttonPanel.add(viewOrderButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(logOutButton);

        // Add components to panel
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

