package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import com.restaurant.model.Manager;

public class ManagerService {

    private final InventoryService inventoryService;

    public ManagerService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Check if the user is a manager before performing actions
    private boolean isManager(Manager manager) {
        return manager != null && "Manager".equalsIgnoreCase(manager.getRole());
    }

    public String addInventoryItem(Manager manager, InventoryItem item) {
        if (isManager(manager)) {
            boolean result = inventoryService.addInventoryItem(item);
            return result ? "Inventory item added successfully." : "Failed to add inventory item.";
        } else {
            return "Permission denied: Only managers can add inventory items.";
        }
    }

    public String removeInventoryItem(Manager manager, int itemId) {
        if (isManager(manager)) {
            boolean result = inventoryService.removeInventoryItem(itemId);
            return result ? "Inventory item removed successfully." : "Failed to remove inventory item.";
        } else {
            return "Permission denied: Only managers can remove inventory items.";
        }
    }

    public String updateInventoryItem(Manager manager, InventoryItem item) {
        if (isManager(manager)) {
            boolean result = inventoryService.updateInventoryItem(item);
            return result ? "Inventory item updated successfully." : "Failed to update inventory item.";
        } else {
            return "Permission denied: Only managers can update inventory items.";
        }
    }
}

