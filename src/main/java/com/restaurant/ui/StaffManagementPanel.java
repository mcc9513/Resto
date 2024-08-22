package com.restaurant.ui;

import com.restaurant.model.User;
import com.restaurant.service.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StaffManagementPanel extends JPanel {
    private UserService userService;
    private JTextField idField, nameField, roleField, hoursWorkedField;
    private JPasswordField passwordField;
    private JTable staffTable;
    private DefaultTableModel tableModel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public StaffManagementPanel(UserService userService, CardLayout cardLayout, JPanel mainPanel) {
        this.userService = userService;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Set layout
        setLayout(new BorderLayout());

        // Top panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));  // Updated to 6 rows
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Role:"));
        roleField = new JTextField();
        inputPanel.add(roleField);

        inputPanel.add(new JLabel("Hours Worked:"));
        hoursWorkedField = new JTextField();
        inputPanel.add(hoursWorkedField);

        // Add password field
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");  // New Clear button
        JButton backButton = new JButton("Main Menu");  // New Main Menu button
        JButton logoutButton = new JButton("Logout");  // New Logout button

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);  // Add the Clear button
        buttonPanel.add(backButton);  // Add the Main Menu button
        buttonPanel.add(logoutButton);  // Add the Logout button

        // Table for displaying staff members
        String[] columns = {"ID", "Name", "Role", "Hours Worked"};
        tableModel = new DefaultTableModel(columns, 0);
        staffTable = new JTable(tableModel);
        loadStaffData();

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(staffTable);

        // Add components to panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add button listeners
        addButton.addActionListener(e -> addStaff());
        editButton.addActionListener(e -> editStaff());
        deleteButton.addActionListener(e -> deleteStaff());
        clearButton.addActionListener(e -> clearFields());  // Clear button listener
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));  // Back to Main Menu listener
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));  // Logout listener

        // Add table selection listener
        staffTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && staffTable.getSelectedRow() != -1) {
                    // Get selected row
                    int selectedRow = staffTable.getSelectedRow();
                    idField.setText(staffTable.getValueAt(selectedRow, 0).toString());
                    nameField.setText(staffTable.getValueAt(selectedRow, 1).toString());
                    roleField.setText(staffTable.getValueAt(selectedRow, 2).toString());
                    hoursWorkedField.setText(staffTable.getValueAt(selectedRow, 3).toString());

                    passwordField.setText("");  // Password won't be shown for security reasons
                }
            }
        });
    }

    private void addStaff() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String role = roleField.getText();
        double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
        String password = new String(passwordField.getPassword());

        User staff = new User(id, name, password, name.charAt(0) + ".", role, hoursWorked);

        if (userService.registerUser(staff)) {
            JOptionPane.showMessageDialog(this, "Staff added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Staff with this ID already exists.");
        }
        loadStaffData();  // Reload data after adding
    }

    private void editStaff() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String role = roleField.getText();
        double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
        String password = new String(passwordField.getPassword());

        User updatedStaff = new User(id, name, password, name.charAt(0) + ".", role, hoursWorked);
        if (userService.updateUser(updatedStaff)) {
            JOptionPane.showMessageDialog(this, "Staff updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Staff with this ID does not exist.");
        }
        loadStaffData();  // Reload data after editing
    }

    private void deleteStaff() {
        int id = Integer.parseInt(idField.getText());
        if (userService.removeUser(id)) {
            JOptionPane.showMessageDialog(this, "Staff removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Staff with this ID does not exist.");
        }
        loadStaffData();  // Reload data after deleting
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        roleField.setText("");
        hoursWorkedField.setText("");
        passwordField.setText("");
    }

    private void loadStaffData() {
        List<User> staffList = userService.getAllUsers();
        tableModel.setRowCount(0);  // Clear existing rows
        for (User staff : staffList) {
            Object[] row = {staff.getId(), staff.getUsername(), staff.getRole(), staff.getHoursWorked()};
            tableModel.addRow(row);
        }
    }
}

