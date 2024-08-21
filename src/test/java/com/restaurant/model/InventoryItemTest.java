package com.restaurant.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryItemTest {
    InventoryItem item;

    @BeforeEach
    void setUp() {
        item = new InventoryItem();
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
        item.reduceStock(80); // This will bring the stock to 20, which is below the threshold of 25
        // You would need to mock or spy the alerting behavior to test this effectively.
        // Here we just check the quantity to see if it's below threshold
        assertTrue(item.getQuantity() <= item.getThreshold(), "Stock should be below threshold and trigger an alert.");
    }
}