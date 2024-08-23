package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import java.util.List;

public class ReportService {
    private OrderService orderService;
    private InventoryService inventoryService;

    public ReportService(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
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

