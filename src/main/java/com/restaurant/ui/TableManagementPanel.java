package com.restaurant.ui;

import com.restaurant.model.Table;
import com.restaurant.service.TableService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TableManagementPanel extends JPanel {
    private TableService tableService;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public TableManagementPanel(TableService tableService, CardLayout cardLayout, JPanel mainPanel) {
        this.tableService = tableService;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Table ID", "Status", "Customer Name", "Reservation Date", "Reservation Time"}, 0);
        JTable tableTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial data from the service
        loadTableData(tableModel);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();

        // Button to assign customer
        JButton assignButton = new JButton("Assign Customer");
        assignButton.addActionListener(e -> assignCustomer(tableTable, tableModel));
        buttonPanel.add(assignButton);

        // Button to change table status
        JButton changeStatusButton = new JButton("Change Status");
        changeStatusButton.addActionListener(e -> changeTableStatus(tableTable, tableModel));
        buttonPanel.add(changeStatusButton);

        // Button to clear the table
        JButton clearButton = new JButton("Clear Table");
        clearButton.addActionListener(e -> clearTable(tableTable, tableModel));
        buttonPanel.add(clearButton);

        // Back to Main Menu Button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MainMenu");
        });
        buttonPanel.add(backButton);

        // Log Out Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Login");
        });
        buttonPanel.add(logoutButton);

        // Add the button panel to the south
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Load table data from the service and populate the JTable
    private void loadTableData(DefaultTableModel tableModel) {
        List<Table> tables = tableService.getAllTables();  // Fetch from table service
        tableModel.setRowCount(0);  // Clear current data
        for (Table table : tables) {
            tableModel.addRow(new Object[]{
                    table.getTableId(),
                    table.getStatus(),
                    table.getCustomerName(),
                    table.getDate() != null ? table.getDate().toString() : "",
                    table.getTime() != null ? table.getTime().toString() : ""
            });
        }
    }

    // Assign a customer to the selected table
    private void assignCustomer(JTable tableTable, DefaultTableModel tableModel) {
        int selectedRow = tableTable.getSelectedRow();
        if (selectedRow >= 0) {
            int tableId = (int) tableModel.getValueAt(selectedRow, 0);
            String customerName = JOptionPane.showInputDialog(this, "Enter Customer Name:");
            if (customerName != null && !customerName.trim().isEmpty()) {
                tableService.assignCustomer(tableId, customerName);  // Update the table with customer name
                loadTableData(tableModel);  // Refresh table data
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a table to assign a customer.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Change the status of the selected table (Open, Seated, Reserved)
    private void changeTableStatus(JTable tableTable, DefaultTableModel tableModel) {
        int selectedRow = tableTable.getSelectedRow();
        if (selectedRow >= 0) {
            int tableId = (int) tableModel.getValueAt(selectedRow, 0);
            String[] statuses = {"Open", "Seated", "Reserved"};
            String newStatus = (String) JOptionPane.showInputDialog(this, "Select Status:", "Change Status",
                    JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);

            if (newStatus != null) {
                if (newStatus.equals("Reserved")) {
                    String reservationDateStr = JOptionPane.showInputDialog(this, "Enter Reservation Date (YYYY-MM-DD):");
                    String reservationTimeStr = JOptionPane.showInputDialog(this, "Enter Reservation Time (HH:MM):");

                    if (reservationDateStr != null && reservationTimeStr != null) {
                        try {
                            LocalDate reservationDate = LocalDate.parse(reservationDateStr);
                            LocalTime reservationTime = LocalTime.parse(reservationTimeStr);

                            tableModel.setValueAt(newStatus, selectedRow, tableModel.findColumn("Status"));
                            tableModel.setValueAt(reservationDate.toString(), selectedRow, tableModel.findColumn("Reservation Date"));
                            tableModel.setValueAt(reservationTime.toString(), selectedRow, tableModel.findColumn("Reservation Time"));

                            // Update the table status with date and time in the service
                            tableService.changeTableStatus(tableId, newStatus, reservationDate, reservationTime);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Invalid date or time format.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    tableModel.setValueAt(newStatus, selectedRow, tableModel.findColumn("Status"));
                    tableService.changeTableStatus(tableId, newStatus);  // Update the table status
                }
                loadTableData(tableModel);  // Refresh table data
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a table to change status.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Clear the selected table
    private void clearTable(JTable tableTable, DefaultTableModel tableModel) {
        int selectedRow = tableTable.getSelectedRow();
        if (selectedRow >= 0) {
            int tableId = (int) tableModel.getValueAt(selectedRow, 0);
            tableService.clearTable(tableId);  // Clear the table
            loadTableData(tableModel);  // Refresh table data
        } else {
            JOptionPane.showMessageDialog(this, "Please select a table to clear.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
