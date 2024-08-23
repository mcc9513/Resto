package com.restaurant.model;

import com.restaurant.service.MenuService;

public class Order {
    private int orderId;
    private int tableId;
    private String menuItem;
    private int quantity;
    private String status; // Added status field

    public Order(int orderId, int tableId, String menuItem, int quantity, String status) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.status = status; // Initialize status
    }

    // Convert an order to a CSV string
    public String toCSV() {
        return orderId + "," + tableId + "," + menuItem + "," + quantity + "," + status; // Include status in CSV
    }

    // Create an order from a CSV string
    public static Order fromCSV(String line) {
        String[] parts = line.split(",");
        int orderId = Integer.parseInt(parts[0]);
        int tableId = Integer.parseInt(parts[1]);
        String menuItem = parts[2];
        int quantity = Integer.parseInt(parts[3]);
        String status = parts[4]; // Extract status from CSV

        return new Order(orderId, tableId, menuItem, quantity, status);
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public int getTableId() { return tableId; }
    public String getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; } // Getter for status

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setTableId(int tableId) { this.tableId = tableId; }
    public void setMenuItem(String menuItem) { this.menuItem = menuItem; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setStatus(String status) { this.status = status; } // Setter for status

    // Method to calculate the total price of the order
    public double getTotalPrice(MenuService menuService) {
        MenuItem menuItem = menuService.getMenuItemByName(this.menuItem);
        if (menuItem != null) {
            return menuItem.getPrice() * this.quantity;
        }
        return 0.0;
    }
}



