package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();  // This will load existing orders from the CSV
    }

    // Test adding an order to the OrderService
    @Test
    void testAddOrder() {
        int nextOrderId = orderService.getNextOrderId();
        Order order = new Order(nextOrderId, 5, "Burger", 2, "Waiting");  // Updated to include status
        orderService.addOrder(order);

        // Verify that the order was added
        List<Order> allOrders = orderService.getAllOrders();
        assertTrue(allOrders.contains(order), "Order should be present in the list of all orders.");
    }

    // Test deleting an order from the OrderService
    @Test
    void testDeleteOrder() {
        int nextOrderId = orderService.getNextOrderId();
        Order order = new Order(nextOrderId, 5, "Burger", 2, "Waiting");  // Updated to include status
        orderService.addOrder(order);

        // Delete the order and verify it's gone
        orderService.deleteOrder(order.getOrderId());
        List<Order> allOrders = orderService.getAllOrders();
        assertFalse(allOrders.contains(order), "Order should be removed from the list of all orders.");
    }

    // Test retrieving all orders from the OrderService
    @Test
    void testGetAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        assertNotNull(orders, "Orders list should not be null.");
        // Optionally check that some orders are already present if the CSV file has existing data
    }

    // Test getting the next available Order ID in OrderService
    @Test
    void testGetNextOrderId() {
        int initialOrderId = orderService.getNextOrderId();
        Order order = new Order(initialOrderId, 5, "Burger", 2, "Waiting");  // Updated to include status
        orderService.addOrder(order);

        // Verify the next ID is incremented correctly
        int nextOrderId = orderService.getNextOrderId();
        assertEquals(initialOrderId + 1, nextOrderId, "Next Order ID should be incremented by 1.");
    }
}

