package com.restaurant.ui;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class MenuManagementPanel extends JPanel {
    private JTable menuTable;
    private JButton addButton, removeButton, editButton;
    private JTextField nameField, priceField;
    private JTextArea descriptionArea;

    public MenuManagementPanel() {
        setLayout(new BorderLayout());

        // Table setup
        menuTable = new JTable((TableModel) new MenuTableModel());
        JScrollPane tableScrollPane = new JScrollPane(menuTable);

        // Form setup
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        nameField = new JTextField();
        priceField = new JTextField();
        descriptionArea = new JTextArea(3, 20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        editButton = new JButton("Edit");
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));
        formPanel.add(addButton);
        formPanel.add(editButton);
        formPanel.add(removeButton);

        // Add components to the panel
        add(tableScrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    // Sample model for the table
    class MenuTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name", "Price", "Description"};
        private Object[][] data = {{"Pizza", 12.99, "Delicious cheese pizza"}, {"Salad", 9.99, "Fresh garden salad"}};

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
    }
}
