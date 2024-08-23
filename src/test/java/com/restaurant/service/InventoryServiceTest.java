package com.restaurant.service;

import com.restaurant.model.InventoryItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventoryService;
    private static final String TEST_CSV_FILE = "Resto/test_inventory.csv";

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialize the InventoryService and set the CSV file path to a test file
        inventoryService = new InventoryService();

        // Use reflection to set the test CSV file path
        Field csvFilePathField = InventoryService.class.getDeclaredField("csvFilePath");
        csvFilePathField.setAccessible(true);
        csvFilePathField.set(inventoryService, TEST_CSV_FILE);

        // Debugging: Verify the CSV file path is set correctly
        System.out.println("CSV File Path: " + csvFilePathField.get(inventoryService));
    }

    @AfterEach
    void tearDown() {
        // Delete the test CSV file after each test
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    // Test adding a new inventory item
    @Test
    void testAddInventoryItem() {
        InventoryItem item = new InventoryItem("Apple", 100, 25, 1.99);
        boolean result = inventoryService.addInventoryItem(item);
        assertTrue(result, "Item should be added successfully.");

        // Debugging: Print out all inventory items to verify the addition
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        System.out.println("Inventory Items After Addition: " + items);

        assertEquals(1, items.size(), "There should be one item in the inventory.");
        assertEquals("Apple", items.get(0).getItemName(), "The added item's name should match.");

        // Print the contents of the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("CSV Content: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Test updating an existing inventory item
    @Test
    void testUpdateInventoryItem() {
        InventoryItem item = new InventoryItem("Apple", 100, 25, 1.99);
        inventoryService.addInventoryItem(item);  // Add item first

        InventoryItem updatedItem = new InventoryItem(1, "Apple", 150, 30, 2.49); // ID = 1
        boolean result = inventoryService.updateInventoryItem(updatedItem);
        assertTrue(result, "Item should be updated successfully.");

        // Debugging: Print out all inventory items to verify the update
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        System.out.println("Inventory Items After Update: " + items);

        assertEquals(1, items.size(), "There should still be one item after the update.");
        assertEquals(150, items.get(0).getQuantity(), "The quantity should be updated to 150.");
        assertEquals(30, items.get(0).getThreshold(), "The threshold should be updated to 30.");
        assertEquals(2.49, items.get(0).getPrice(), 0.01, "The price should be updated to 2.49.");

        // Print the contents of the CSV file after the update
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("CSV Content After Update: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Test removing an inventory item by its ID
    @Test
    void testRemoveInventoryItem() {
        InventoryItem item = new InventoryItem("Apple", 100, 25, 1.99);
        inventoryService.addInventoryItem(item);  // Add item first

        boolean result = inventoryService.removeInventoryItem(1);  // Remove item with ID 1
        assertTrue(result, "Item should be removed successfully.");

        // Debugging: Print out all inventory items to verify the removal
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        System.out.println("Inventory Items After Removal: " + items);

        assertEquals(0, items.size(), "There should be no items left after removal.");

        // Print the contents of the CSV file after removal
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("CSV Content After Removal: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Test retrieving all inventory items from CSV
    @Test
    void testGetAllInventoryItems() {
        InventoryItem item1 = new InventoryItem("Apple", 100, 25, 1.99);
        InventoryItem item2 = new InventoryItem("Orange", 50, 10, 0.99);
        inventoryService.addInventoryItem(item1);
        inventoryService.addInventoryItem(item2);

        // Debugging: Print out all inventory items
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        System.out.println("Inventory Items: " + items);

        assertEquals(2, items.size(), "There should be two items in the inventory.");
        assertEquals("Apple", items.get(0).getItemName(), "First item should be Apple.");
        assertEquals("Orange", items.get(1).getItemName(), "Second item should be Orange.");

        // Print the contents of the CSV file after adding multiple items
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("CSV Content: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

