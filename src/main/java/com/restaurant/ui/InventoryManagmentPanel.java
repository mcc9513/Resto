package com.restaurant.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableManagementPanel extends JPanel {
    private JTable tableList;
    private JButton addButton, reserveButton, freeButton;
    private JTextField tableIDField, capacityField;

    public TableManagementPanel() {
        setLayout(new BorderLayout());

        // Table setup
        tableList = new JTable(new TableTableModel());
        JScrollPane scrollPane = new JScrollPane(tableList);

        // Form setup
        JPanel formPanel = new JPanel(new GridLayout(0, 2)); // 0 rows, 2 columns to auto-adjust rows based on content
        tableIDField = new JTextField();
        capacityField = new JTextField();
        addButton = new JButton("Add Table");
        reserveButton = new JButton("Reserve Table");
        freeButton = new JButton("Free Table");

        // Adding components to the form panel
        formPanel.add(new JLabel("Table ID:"));
        formPanel.add(tableIDField);
        formPanel.add(new JLabel("Capacity:"));
        formPanel.add(capacityField);
        formPanel.add(addButton);
        formPanel.add(reserveButton);
        formPanel.add(freeButton);

        // Add components to the main panel
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTable();
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveTable();
            }
        });

        freeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freeTable();
            }
        });
    }

    private void addTable() {
        // Logic to add a new table
        String tableId = tableIDField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        ((TableTableModel) tableList.getModel()).addRow(new Object[]{tableId, capacity, "Available"});
    }

    private void reserveTable() {
        // Logic to reserve a selected table
        int selectedRow = tableList.getSelectedRow();
        if (selectedRow >= 0) {
            tableList.setValueAt("Reserved", selectedRow, 2);
        }
    }

    private void freeTable() {
        // Logic to free a selected table
        int selectedRow = tableList.getSelectedRow();
        if (selectedRow >= 0) {
            tableList.setValueAt("Available", selectedRow, 2);
        }
    }

    class TableTableModel extends AbstractTableModel {
        private String[] columnNames = {"Table ID", "Capacity", "Status"};
        private Object[][] data = {{"1", 4, "Available"}, {"2", 2, "Occupied"}, {"3", 6, "Reserved"}};

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        public void addRow(Object[] row) {
            Object[][] newData = new Object[data.length + 1][];
            System.arraycopy(data, 0, newData, 0, data.length);
            newData[data.length] = row;
            data = newData;
            fireTableDataChanged();
        }
    }
}
