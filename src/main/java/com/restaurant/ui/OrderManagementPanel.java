package com.restaurant.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class OrderManagementPanel extends JPanel {
    private JTable orderTable;
    private JButton addButton, removeButton, updateStatusButton;

    public OrderManagementPanel() {
        setLayout(new BorderLayout());

        // Table for orders
        orderTable = new JTable((TableModel) new OrderTableModel());
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // Buttons for order management
        addButton = new JButton("Add Order");
        removeButton = new JButton("Remove Order");
        updateStatusButton = new JButton("Update Status");

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateStatusButton);

        // Add components to panel
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Sample model for the order table
    class OrderTableModel extends AbstractTableModel {
        private String[] columnNames = {"Order ID", "Item", "Status"};
        private Object[][] data = {{"1", "Pizza", "Preparing"}, {"2", "Salad", "Ready"}};

        public int getColumnCount() {
            return columnNames.length;
        }

        the getRowCount() {
            return data.length;
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}
