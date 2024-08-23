package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventoryService;
    private static final String TEST_CSV_FILE = "test_inventory.csv";

    @BeforeEach
    void setUp() {
        // Use a test-specific CSV file
        inventoryService = new InventoryService() {
            private String getCsvFilePath() {
                return TEST_CSV_FILE;
            }
        };
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after each test
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddInventoryItem() {
        InventoryItem item = new InventoryItem(0, "Test Item", 10, 5, 9.99);
        boolean result = inventoryService.addInventoryItem(item);
        assertTrue(result);

        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        assertEquals(1, items.size());
        assertEquals("Test Item", items.get(0).getItemName());
    }

    @Test
    void testUpdateInventoryItem() {
        InventoryItem item = new InventoryItem(1, "Original Item", 10, 5, 9.99);
        inventoryService.addInventoryItem(item);

        InventoryItem updatedItem = new InventoryItem(1, "Updated Item", 15, 7, 14.99);
        boolean result = inventoryService.updateInventoryItem(updatedItem);
        assertTrue(result);

        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        assertEquals(1, items.size());
        assertEquals("Updated Item", items.get(0).getItemName());
        assertEquals(15, items.get(0).getQuantity());
    }

    @Test
    void testRemoveInventoryItem() {
        InventoryItem item = new InventoryItem(1, "Item to Remove", 10, 5, 9.99);
        inventoryService.addInventoryItem(item);

        boolean result = inventoryService.removeInventoryItem(1);
        assertTrue(result);

        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        assertEquals(0, items.size());
    }

    @Test
    void testGetAllInventoryItems() {
        InventoryItem item1 = new InventoryItem(0, "Item 1", 10, 5, 9.99);
        InventoryItem item2 = new InventoryItem(0, "Item 2", 20, 8, 14.99);
        inventoryService.addInventoryItem(item1);
        inventoryService.addInventoryItem(item2);

        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        assertEquals(2, items.size());
        assertEquals("Item 1", items.get(0).getItemName());
        assertEquals("Item 2", items.get(1).getItemName());
    }
}

//    @Test
//    void testAddItemToCSV() {
//        InventoryItem item = new InventoryItem(1, "CSV Item", 10, 5, 9.99);
//        boolean result = InventoryService.saveAllItemsToCSV();
//        assertTrue(result);
//
//        List<InventoryItem> items = inventoryService.getAllInventoryItems();
//        assertEquals(1, items.size());
//        assertEquals("CSV Item", items.get(0).getItemName());
//    }
//}