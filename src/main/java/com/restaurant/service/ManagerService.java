package com.restaurant.service;


import com.restaurant.model.InventoryItem;
import com.restaurant.model.Manager;

public class ManagerService {

    private final InventoryService inventoryService;

    public ManagerService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public String addInventoryItem(Manager manager, InventoryItem item) {
        if (manager.isManager()) {
            boolean success = inventoryService.addInventoryItem(item);
            return success ? "Inventory item added successfully." : "Failed to add inventory item.";
        } else {
            return "Permission denied: Only managers can add inventory items.";
        }
    }

    public String removeInventoryItem(Manager user, InventoryItem item) {
        if (!user.getRole().equals("Manager")) {
            return "Permission denied: Only managers can add inventory items.";
        }
        boolean success = inventoryService.removeInventoryItem(item.getItemId());
        return success ? "Inventory item removed successfully." : "Failed to remove inventory item.";
    }

    public String updateInventoryItem(Manager user, InventoryItem item) {
        if (!user.getRole().equals("Manager")) {
            return "Permission denied: Only managers can add inventory items.";
        }
        boolean success = inventoryService.updateInventoryItem(item);
        return success ? "Inventory item updated successfully." : "Failed to update inventory item.";
    }

    private boolean isManager(Manager manager) {
        return "Manager".equals(manager.getRole());
    }
}