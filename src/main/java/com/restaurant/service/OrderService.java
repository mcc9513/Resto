package com.restaurant.service;

import com.restaurant.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private final String csvFilePath = "orders.csv";  // Path to the orders CSV file

    public OrderService() {
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
        saveOrdersToCSV();  // Save to CSV after adding
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
            bw.write("OrderID,TableID,MenuItem,Quantity");
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
                Order order = Order.fromCSV(line);
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
