package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.model.InventoryItem;
import java.util.List;

public class ReportService {
    private OrderService orderService;
    private InventoryService inventoryService;

    public ReportService(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    // Get the total number of orders placed
    public int getTotalOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders.size();
    }

    // Get the total inventory count
    public int getTotalInventoryCount() {
        List<InventoryItem> inventory = inventoryService.getAllInventoryItems();
        return inventory.size();
    }

    // Get the total inventory value (sum of all items in stock)
    public double getTotalInventoryValue() {
        List<InventoryItem> inventory = inventoryService.getAllInventoryItems();
        double totalValue = 0;
        for (InventoryItem item : inventory) {
            totalValue += item.getQuantity() * item.getPrice();
        }
        return totalValue;
    }

    // Get the total sales value (based on orders)
    public double getTotalSalesValue() {
        List<Order> orders = orderService.getAllOrders();
        double totalSales = 0;
        for (Order order : orders) {
            totalSales += order.getQuantity() * getMenuItemPrice(order.getMenuItem());
        }
        return totalSales;
    }

    // Helper method to get the price of a menu item from the inventory
    private double getMenuItemPrice(String menuItemName) {
        List<InventoryItem> inventory = inventoryService.getAllInventoryItems();
        for (InventoryItem item : inventory) {
            if (item.getItemName().equals(menuItemName)) {
                return item.getPrice();
            }
        }
        return 0;
    }
}

