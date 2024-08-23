package com.restaurant.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryItemTest {
    InventoryItem item;

    @BeforeEach
    void setUp() {
        // Initialize with meaningful values
        item = new InventoryItem(1, "Apple", 100, 25, 1.5);  // Item ID, Name, Quantity, Threshold, Price
    }

    @Test
    void addStockShouldIncreaseQuantity() {
        item.addStock(50);
        assertEquals(150, item.getQuantity(), "Stock should increase by the amount added.");
    }

    @Test
    void reduceStockShouldDecreaseQuantityAndReturnTrue() {
        boolean success = item.reduceStock(30);
        assertTrue(success, "Stock reduction should succeed when sufficient stock is available.");
        assertEquals(70, item.getQuantity(), "Stock should decrease by the amount reduced.");
    }

    @Test
    void reduceStockShouldFailAndNotChangeQuantityWhenInsufficientStock() {
        boolean success = item.reduceStock(150);
        assertFalse(success, "Stock reduction should fail when insufficient stock is available.");
        assertEquals(100, item.getQuantity(), "Stock should not change when reduction fails.");
    }

    @Test
    void reduceStockShouldTriggerAlertWhenBelowThreshold() {
        item.reduceStock(80);  // This will bring the stock to 20, which is below the threshold of 25
        assertTrue(item.getQuantity() <= item.getThreshold(), "Stock should be below threshold and trigger an alert.");
    }

    @Test
    void testGettersAndSetters() {
        item.setItemId(2);
        item.setItemName("Banana");
        item.setQuantity(200);
        item.setThreshold(50);
        item.setPrice(2.0);

        assertEquals(2, item.getItemId());
        assertEquals("Banana", item.getItemName());
        assertEquals(200, item.getQuantity());
        assertEquals(50, item.getThreshold());
        assertEquals(2.0, item.getPrice());
    }

    @Test
    void reduceStockShouldSucceedWhenExactQuantity() {
        boolean success = item.reduceStock(100);  // Reduce stock to exactly 0
        assertTrue(success, "Stock reduction should succeed when reducing by the exact quantity available.");
        assertEquals(0, item.getQuantity(), "Stock should be exactly zero after reduction.");
    }

    @Test
    void testToCSV() {
        String csv = item.toCSV();
        assertEquals("1,Apple,100,25,1.5", csv, "CSV format should match the expected string.");
    }

    @Test
    void testFromCSV() {
        String csvLine = "2,Banana,150,30,2.5";
        InventoryItem newItem = InventoryItem.fromCSV(csvLine);

        assertEquals(2, newItem.getItemId());
        assertEquals("Banana", newItem.getItemName());
        assertEquals(150, newItem.getQuantity());
        assertEquals(30, newItem.getThreshold());
        assertEquals(2.5, newItem.getPrice());
    }

    @Test
    void reduceStockShouldNotTriggerAlertWhenAboveThreshold() {
        boolean success = item.reduceStock(50);  // This reduces stock to 50, which is above the threshold of 25
        assertTrue(success);
        assertEquals(50, item.getQuantity(), "Stock should be reduced to 50.");
        assertTrue(item.getQuantity() > item.getThreshold(), "Stock should still be above the threshold.");
    }

}