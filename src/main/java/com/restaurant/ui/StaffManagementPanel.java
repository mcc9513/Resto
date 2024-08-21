package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;

public class StaffManagementPanel extends JPanel {
    private JTable staffTable;
    private JButton addStaffButton, editStaffButton, removeStaffButton;

    public StaffManagementPanel() {
        setLayout(new BorderLayout());

        // Staff Table
        String[] columns = {"Staff ID", "Name", "Role", "Status"};
        Object[][] data = {
                {"101", "John Doe", "Chef", "Active"},
                {"102", "Jane Smith", "Waiter", "On Leave"}
        };
        staffTable = new JTable(data, columns);
        JScrollPane tableScroll = new JScrollPane(staffTable);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addStaffButton = new JButton("Add Staff");
        editStaffButton = new JButton("Edit Staff");
        removeStaffButton = new JButton("Remove Staff");
        buttonPanel.add(addStaffButton);
        buttonPanel.add(editStaffButton);
        buttonPanel.add(removeStaffButton);

        // Add components to panel
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

