package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    private ReportService reportService;
    private OrderService orderService;
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        // Create mock services
        orderService = mock(OrderService.class);
        inventoryService = mock(InventoryService.class);

        // Initialize ReportService with mock services
        reportService = new ReportService(orderService, inventoryService);
    }

    // Test getting the price of a menu item that exists in the inventory
    @Test
    void testGetMenuItemPriceFound() {
        // Set up mock inventory
        InventoryItem item1 = new InventoryItem(1, "Burger", 10, 5, 9.99);
        InventoryItem item2 = new InventoryItem(2, "Pizza", 20, 5, 12.99);
        List<InventoryItem> inventory = Arrays.asList(item1, item2);

        // Mock the behavior of InventoryService to return the mock inventory
        when(inventoryService.getAllInventoryItems()).thenReturn(inventory);

        // Call the method under test
        double price = reportService.getMenuItemPrice("Pizza");

        // Verify the correct price is returned
        assertEquals(12.99, price, 0.01, "The price of 'Pizza' should be 12.99.");
    }

    // Test getting the price of a menu item that does not exist in the inventory
    @Test
    void testGetMenuItemPriceNotFound() {
        // Set up mock inventory
        InventoryItem item1 = new InventoryItem(1, "Burger", 10, 5, 9.99);
        InventoryItem item2 = new InventoryItem(2, "Pizza", 20, 5, 12.99);
        List<InventoryItem> inventory = Arrays.asList(item1, item2);

        // Mock the behavior of InventoryService to return the mock inventory
        when(inventoryService.getAllInventoryItems()).thenReturn(inventory);

        // Call the method under test for a non-existent item
        double price = reportService.getMenuItemPrice("Pasta");

        // Verify the price returned is 0 for an item that does not exist
        assertEquals(0.0, price, "The price of 'Pasta' should be 0.0 as it does not exist in the inventory.");
    }

    // Test getting the price when the inventory is empty
    @Test
    void testGetMenuItemPriceWithEmptyInventory() {
        // Mock the behavior of InventoryService to return an empty inventory
        when(inventoryService.getAllInventoryItems()).thenReturn(Arrays.asList());

        // Call the method under test
        double price = reportService.getMenuItemPrice("Burger");

        // Verify the price returned is 0 when the inventory is empty
        assertEquals(0.0, price, "The price of 'Burger' should be 0.0 as the inventory is empty.");
    }
}

