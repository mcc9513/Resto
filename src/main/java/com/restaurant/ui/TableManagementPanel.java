package com.restaurant.ui;

import com.restaurant.model.Table;
import com.restaurant.service.TableService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Table ID", "Table Size", "Status", "Party Size", "Reservation Name", "Reservation Time"}, 0);
        JTable tableTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial data from the service
        loadTableData(tableModel);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();

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
                    table.getTableSize(),
                    table.getStatus(),
                    table.getPartySize(),
                    table.getReservationName(),
                    table.getReservationTime() != null ? table.getReservationTime().toString() : ""
            });
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
                    String partySizeStr = JOptionPane.showInputDialog(this, "Enter Party Size:");
                    String reservationName = JOptionPane.showInputDialog(this, "Enter Reservation Name:");
                    String reservationTimeStr = JOptionPane.showInputDialog(this, "Enter Reservation Time (HH:MM):");

                    if (partySizeStr != null && reservationName != null && reservationTimeStr != null) {
                        try {
                            int partySize = Integer.parseInt(partySizeStr);
                            LocalTime reservationTime = LocalTime.parse(reservationTimeStr);

                            if (!tableService.changeTableStatusToReserved(tableId, partySize, reservationName, reservationTime)) {
                                JOptionPane.showMessageDialog(this, "Party size exceeds table size.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                loadTableData(tableModel);  // Refresh table data
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (newStatus.equals("Seated")) {
                    String partySizeStr = JOptionPane.showInputDialog(this, "Enter Party Size:");

                    if (partySizeStr != null) {
                        try {
                            int partySize = Integer.parseInt(partySizeStr);

                            if (!tableService.changeTableStatusToSeated(tableId, partySize)) {
                                JOptionPane.showMessageDialog(this, "Party size exceeds table size.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                loadTableData(tableModel);  // Refresh table data
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    tableService.clearTable(tableId);  // Reset to Open
                    loadTableData(tableModel);  // Refresh table data
                }
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
