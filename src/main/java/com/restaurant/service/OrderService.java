package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.model.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
    private Map<Integer, Order> orders; // Manage orders by their ID

    public OrderService() {
        this.orders = new HashMap<>();
    }

    // Create a new order
    public Order createOrder(int orderId, int tableId) {
        if (orders.containsKey(orderId)) {
            throw new IllegalStateException("Order with this ID already exists.");
        }
        Order newOrder = new Order(orderId, tableId);
        orders.put(orderId, newOrder);
        return newOrder;
    }

    // Add an item to an order
    public void addItemToOrder(int orderId, MenuItem item) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order does not exist.");
        }
        order.addItem(item);
    }

    // Remove an item from an order
    public boolean removeItemFromOrder(int orderId, MenuItem item) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order does not exist.");
        }
        return order.removeItem(item);
    }

    // Update the status of an order
    public void updateOrderStatus(int orderId, String status) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order does not exist.");
        }
        order.setStatus(status);
    }

    // Get an order by its ID
    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }
}
