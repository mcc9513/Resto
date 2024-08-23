package com.restaurant.ui;

import com.restaurant.model.Order;
import com.restaurant.service.OrderService;
import com.restaurant.service.MenuService;
import com.restaurant.model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class OrderManagementPanel extends JPanel {
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private OrderService orderService;
    private MenuService menuService;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public OrderManagementPanel(OrderService orderService, MenuService menuService, CardLayout cardLayout, JPanel mainPanel) {
        this.orderService = orderService;
        this.menuService = menuService;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create table model with column names, including "Status"
        tableModel = new DefaultTableModel(new String[]{"Order ID", "Table ID", "Menu Item", "Quantity", "Status"}, 0);
        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial order data
        loadOrderData();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();

        // Button to create a new order
        JButton createOrderButton = new JButton("Create Order");
        createOrderButton.addActionListener(e -> {
            try {
                createOrder();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(createOrderButton);

        // Button to update the status of an order
        JButton updateStatusButton = new JButton("Update Status");
        updateStatusButton.addActionListener(e -> updateOrderStatus());
        buttonPanel.add(updateStatusButton);

        // Button to delete an order
        JButton deleteOrderButton = new JButton("Delete Order");
        deleteOrderButton.addActionListener(e -> deleteOrder());
        buttonPanel.add(deleteOrderButton);

        // Back to Main Menu button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "MainMenu");
        });
        buttonPanel.add(backButton);

        // Log Out button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Login");
        });
        buttonPanel.add(logoutButton);

        // Add the button panel to the south
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Load order data into the JTable
    private void loadOrderData() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Order> orders = orderService.getAllOrders();

        if (orders != null) { // Check if orders is not null
            for (Order order : orders) {
                if (order.getMenuItem() != null) { // Ensure menuItem is not null
                    tableModel.addRow(new Object[]{
                            order.getOrderId(),
                            order.getTableId(),
                            order.getMenuItem().getName(),  // Use getName() to display the menu item name
                            order.getQuantity(),
                            order.getStatus() // Display status
                    });
                } else {
                    tableModel.addRow(new Object[]{
                            order.getOrderId(),
                            order.getTableId(),
                            "Unknown",  // Handle the case where menuItem is null
                            order.getQuantity(),
                            order.getStatus() // Display status even if menu item is unknown
                    });
                }
            }
        }
    }

    // Create a new order with dropdowns for table ID and menu items
    private void createOrder() throws IOException {
        // Dropdown for Table ID (1-13)
        Integer[] tableIds = IntStream.rangeClosed(1, 13).boxed().toArray(Integer[]::new);
        JComboBox<Integer> tableIdDropdown = new JComboBox<>(tableIds);

        // Dropdown for Menu Items (loaded from the MenuService)
        List<MenuItem> menuItems = menuService.loadMenuItems();
        String[] menuItemNames = menuItems.stream().map(MenuItem::getName).toArray(String[]::new);
        JComboBox<String> menuItemDropdown = new JComboBox<>(menuItemNames);

        // Field for quantity
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Table ID:"));
        panel.add(tableIdDropdown);
        panel.add(new JLabel("Menu Item:"));
        panel.add(menuItemDropdown);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Create New Order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int tableId = (int) tableIdDropdown.getSelectedItem();
                String menuItemName = (String) menuItemDropdown.getSelectedItem();
                MenuItem menuItem = menuService.getMenuItemByName(menuItemName);
                int quantity = Integer.parseInt(quantityField.getText().trim());

                // Create new order and add it with "waiting" status
                int orderId = orderService.getNextOrderId();  // Generate a new order ID
                Order newOrder = new Order(orderId, tableId, menuItemName, quantity, "waiting");
                orderService.addOrder(newOrder);
                loadOrderData();  // Reload the table
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Update the status of the selected order
    private void updateOrderStatus() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            int orderId = (int) tableModel.getValueAt(selectedRow, 0);  // Get the order ID
            Order order = orderService.getOrderById(orderId);  // Get the order by ID

            // Create a dialog to select the new status
            String[] statuses = {"Waiting", "Served"};
            String newStatus = (String) JOptionPane.showInputDialog(this,
                    "Select new status:", "Update Status",
                    JOptionPane.PLAIN_MESSAGE, null, statuses, order.getStatus());

            if (newStatus != null && !newStatus.equals(order.getStatus())) {
                order.setStatus(newStatus);  // Update the order status
                orderService.updateOrder(order);  // Save the updated order
                loadOrderData();  // Reload the table
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to update.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Delete the selected order
    private void deleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            int orderId = (int) tableModel.getValueAt(selectedRow, 0);  // Get the order ID
            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Order ID " + orderId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                orderService.deleteOrder(orderId);  // Delete the order from the service
                loadOrderData();  // Reload the table
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}






