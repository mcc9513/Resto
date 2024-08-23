package com.restaurant.model;

import com.restaurant.service.MenuService;


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

    // Method to calculate the total price of the order
    public double getTotalPrice(MenuService menuService) {
        MenuItem menuItem = menuService.getMenuItemByName(this.menuItem);
        if (menuItem != null) {
            return menuItem.getPrice() * this.quantity;
        }
        return 0.0;
    }
    public MenuItem getMenuItemByName(MenuService menuService,String name) {
        MenuItem menuItem = menuService.getMenuItemByName(name);
        return menuItem;
    }

    public MenuItem getMenuItemObject (MenuService menuService) {
        return menuService.getMenuItemByName(this.menuItem);
    }
}



