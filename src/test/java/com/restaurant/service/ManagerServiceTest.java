package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import com.restaurant.model.Manager;
import com.restaurant.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ManagerServiceTest {

    @Mock
    private InventoryService inventoryService;

    private ManagerService managerService;
    // Declare itemId here if used in multiple tests
    private int itemId;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        managerService = new ManagerService(inventoryService);

        // Additional mock setup if needed
    }


    private Manager createManager(boolean isManager) {
        // The role is set to "Manager" for managerial users
        return new Manager(1, "username", "passwordHash", "J D", isManager ? 40.0 : 0.0);
    }

    private InventoryItem createInventoryItem() {
        return new InventoryItem(1, "Test Item", 10, 5, 9.99);
    }

    @Test
    void testAddInventoryItem_AsManager() {
        Manager manager = createManager(true); // Creates a manager with role "Manager"
        InventoryItem item = createInventoryItem();

        when(inventoryService.addInventoryItem(any(InventoryItem.class))).thenReturn(true);

        String result = managerService.addInventoryItem(manager, item);
        assertEquals("Inventory item added successfully.", result);
        verify(inventoryService).addInventoryItem(any(InventoryItem.class));
    }

    @Test
    void testAddInventoryItem_NotManager() {
        Manager notManager = createManager(false); // Creates a non-manager with role "Manager"
        InventoryItem item = createInventoryItem();

        String result = managerService.addInventoryItem(notManager, item);
        assertEquals("Permission denied: Only managers can add inventory items.", result);
        verify(inventoryService, never()).addInventoryItem(any());
    }

    @Test
    void testRemoveInventoryItem_AsManager() {
        Manager manager = createManager(true); // Creates a manager with role "Manager"
        InventoryItem item = createInventoryItem();

        when(inventoryService.removeInventoryItem(itemId)).thenReturn(true);

        String result = managerService.removeInventoryItem(manager, item);
        assertEquals("Inventory item removed successfully.", result);
        verify(inventoryService).removeInventoryItem(itemId);
    }

    @Test
    void testRemoveInventoryItem_NotManager() {
        Manager notManager = createManager(false); // Creates a non-manager with role "Manager"
        InventoryItem item = createInventoryItem();

        String result = managerService.removeInventoryItem(notManager, item);
        assertEquals("Permission denied: Only managers can remove inventory items.", result);
        verify(inventoryService, never()).removeInventoryItem(anyInt());
    }

    @Test
    void testUpdateInventoryItem_AsManager() {
        Manager manager = createManager(true); // Creates a manager with role "Manager"
        InventoryItem item = createInventoryItem();

        when(inventoryService.updateInventoryItem(any(InventoryItem.class))).thenReturn(true);

        String result = managerService.updateInventoryItem(manager, item);
        assertEquals("Inventory item updated successfully.", result);
        verify(inventoryService).updateInventoryItem(any(InventoryItem.class));
    }

    @Test
    void testUpdateInventoryItem_NotManager() {
        Manager notManager = createManager(false); // Creates a non-manager with role "Manager"
        InventoryItem item = createInventoryItem();

        String result = managerService.updateInventoryItem(notManager, item);
        assertEquals("Permission denied: Only managers can update inventory items.", result);
        verify(inventoryService, never()).updateInventoryItem(any());
    }

    @Test
    void testInventoryOperations_FailureScenarios() {
        Manager manager = createManager(true); // Creates a manager with role "Manager"
        InventoryItem item = createInventoryItem();

        when(inventoryService.addInventoryItem(any(InventoryItem.class))).thenReturn(false);
        when(inventoryService.removeInventoryItem(anyInt())).thenReturn(false);
        when(inventoryService.updateInventoryItem(any(InventoryItem.class))).thenReturn(false);

        assertEquals("Failed to add inventory item.", managerService.addInventoryItem(manager, item));
        assertEquals("Failed to remove inventory item.", managerService.removeInventoryItem(manager, item));
        assertEquals("Failed to update inventory item.", managerService.updateInventoryItem(manager, item));
    }
}
