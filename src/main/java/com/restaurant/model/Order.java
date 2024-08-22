package com.restaurant.model;

public class Order {
    private int orderId;
    private int tableId;
    private String menuItem;
    private int quantity;

    public Order(int orderId, int tableId, String menuItem, int quantity) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // Convert an order to a CSV string
    public String toCSV() {
        return orderId + "," + tableId + "," + menuItem + "," + quantity;
    }

    // Create an order from a CSV string
    public static Order fromCSV(String line) {
        String[] parts = line.split(",");
        int orderId = Integer.parseInt(parts[0]);
        int tableId = Integer.parseInt(parts[1]);
        String menuItem = parts[2];
        int quantity = Integer.parseInt(parts[3]);

        return new Order(orderId, tableId, menuItem, quantity);
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public int getTableId() { return tableId; }
    public String getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setTableId(int tableId) { this.tableId = tableId; }
    public void setMenuItem(String menuItem) { this.menuItem = menuItem; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}


