package com.restaurant.model;

import com.restaurant.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize an order object with example data, including status
        order = new Order(1, 101, "Burger", 3, "Waiting");  // Order ID, Table ID, MenuItem, Quantity, Status
    }

    // Test the CSV conversion of an order object
    @Test
    void testToCSV() {
        String csv = order.toCSV();
        assertEquals("1,101,Burger,3,Waiting", csv, "CSV format should match the expected string with status.");
    }

    // Test creating an order object from a CSV string
    @Test
    void testFromCSV() {
        String csvLine = "2,102,Pizza,4,Served";
        Order newOrder = Order.fromCSV(csvLine);

        assertEquals(2, newOrder.getOrderId());
        assertEquals(102, newOrder.getTableId());
        assertEquals("Pizza", newOrder.getMenuItem());
        assertEquals(4, newOrder.getQuantity());
        assertEquals("Served", newOrder.getStatus());
    }

    // Test calculating total price with a mock MenuService
    @Test
    void testGetTotalPrice() {
        // Mock the MenuService
        MenuService menuService = mock(MenuService.class);

        // Mock a MenuItem to return a specific price when queried
        MenuItem menuItem = mock(MenuItem.class);
        when(menuItem.getPrice()).thenReturn(10.99);  // Mock the getPrice() method
        when(menuService.getMenuItemByName("Burger")).thenReturn(menuItem);

        // Verify that total price is calculated correctly (10.99 * 3 = 32.97)
        double totalPrice = order.getTotalPrice(menuService);
        assertEquals(32.97, totalPrice, 0.01, "Total price should match expected calculation.");

        // Test for a non-existent menu item
        when(menuService.getMenuItemByName("NonExistentItem")).thenReturn(null);
        Order invalidOrder = new Order(2, 102, "NonExistentItem", 1, "Waiting");
        double invalidTotalPrice = invalidOrder.getTotalPrice(menuService);
        assertEquals(0.0, invalidTotalPrice, "Total price should be 0.0 when menu item is not found.");
    }

    // Test the individual getters for the order fields
    @Test
    void testGetters() {
        assertEquals(1, order.getOrderId());
        assertEquals(101, order.getTableId());
        assertEquals("Burger", order.getMenuItem());
        assertEquals(3, order.getQuantity());
        assertEquals("Waiting", order.getStatus());
    }

    // Test the individual setters for the order fields, including status
    @Test
    void testSetters() {
        order.setOrderId(2);
        order.setTableId(102);
        order.setMenuItem("Pizza");
        order.setQuantity(4);
        order.setStatus("Served");

        assertEquals(2, order.getOrderId());
        assertEquals(102, order.getTableId());
        assertEquals("Pizza", order.getMenuItem());
        assertEquals(4, order.getQuantity());
        assertEquals("Served", order.getStatus());
    }
}


