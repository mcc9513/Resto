package com.restaurant.service;

// In ManagerService.java

import com.restaurant.model.InventoryItem;
import com.restaurant.model.Manager;

public class ManagerService {

    private InventoryService inventoryService;

    public ManagerService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Check if the user is a manager before performing actions
    private boolean isManager(Manager manager) {
        return manager != null && "Manager".equals(manager.getRole());
    }

    public void addInventoryItem(Manager manager, InventoryItem item) {
        if (isManager(manager)) {
            inventoryService.addInventoryItem(item);
        } else {
            System.out.println("Permission denied: Only managers can add inventory items.");
        }
    }

    public void removeInventoryItem(Manager manager, int itemId) {
        if (isManager(manager)) {
            inventoryService.removeInventoryItem(itemId);
        } else {
            System.out.println("Permission denied: Only managers can remove inventory items.");
        }
    }

    public void updateInventoryItem(Manager manager, InventoryItem item) {
        if (isManager(manager)) {
            inventoryService.updateInventoryItem(item);
        } else {
            System.out.println("Permission denied: Only managers can update inventory items.");
        }
    }
}

