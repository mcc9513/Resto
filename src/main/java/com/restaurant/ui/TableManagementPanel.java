package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class TableManagementPanel extends JPanel {
    private JTable tableStatusTable;
    private JButton reserveButton, releaseButton, mainButton, logOutButton;

    public TableManagementPanel() {
        setLayout(new BorderLayout());

        // Table status table
        String[] columns = {"Table No.", "Capacity", "Status"};
        Object[][] data = {
                {"1", "4", "Available"},
                {"2", "2", "Occupied"},
        };
        tableStatusTable = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(tableStatusTable);

        // Buttons
        JPanel buttonPanel = new JPanel();
        reserveButton = new JButton("Reserve Table");
        releaseButton = new JButton("Release Table");
        mainButton = new JButton("Home");
        logOutButton = new JButton("Log Out");
        buttonPanel.add(reserveButton);
        buttonPanel.add(releaseButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(logOutButton);

        // Add components to panel
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

