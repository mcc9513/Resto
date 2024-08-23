package com.restaurant.service;

import com.restaurant.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private final String csvFilePath = "orders.csv";  // Path to the orders CSV file
    private RestaurantController controller;

    public OrderService() {
        this.controller = controller;
        // Load existing orders from the CSV file at initialization
        loadOrdersFromCSV();
    }

    // Fetch all orders
    public List<Order> getAllOrders() {
        return orders;
    }

    // Add a new order
    public void addOrder(Order order) {
        orders.add(order);
        InventoryService inv = new InventoryService();
        inv.reduceIngredientsForMenuItem(order.getMenuItem());
        saveOrdersToCSV();  // Save to CSV after adding
    }

    // Update an existing order by ID
    public void updateOrder(int orderId, Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId() == orderId) {
                orders.set(i, updatedOrder);
                break;
            }
        }
        saveOrdersToCSV();  // Save to CSV after updating
    }

    // Get a specific order by ID
    public Order getOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    // Delete an order by ID
    public void deleteOrder(int orderId) {
        orders.removeIf(order -> order.getOrderId() == orderId);
        saveOrdersToCSV();  // Save to CSV after deleting
    }

    // Generate the next available Order ID
    public int getNextOrderId() {
        int maxId = 0;
        for (Order order : orders) {
            if (order.getOrderId() > maxId) {
                maxId = order.getOrderId();
            }
        }
        return maxId + 1;
    }

    // Save all orders to CSV file
    private void saveOrdersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write header
            bw.write("OrderID,TableID,MenuItem,Quantity,Status"); // Include Status in header
            bw.newLine();
            // Write each order
            for (Order order : orders) {
                bw.write(order.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all orders from CSV file
    private void loadOrdersFromCSV() {
        File file = new File(csvFilePath);
        if (!file.exists()) {
            return;  // If file doesn't exist, there's nothing to load
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip the header
                    continue;
                }
                Order order = Order.fromCSV(line); // Load the order, including status
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

