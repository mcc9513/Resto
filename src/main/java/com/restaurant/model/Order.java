package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<MenuItem> itemsOrdered;
    private double totalPrice;
    private String status;
    private int tableId;


    // Constructor
    public Order(int orderID, int tableID) {
        this.orderId = orderID;
        this.tableId = tableID;
        this.itemsOrdered = new ArrayList<>();
        this.totalPrice = 0.0;
        this.status = "Waiting";
    }

    // Empty Constructor for Edge Cases
    public Order() {

    }

    // Add an item to the order
    public void addItem(MenuItem item) {
        itemsOrdered.add(item);
        calculateTotalPrice();
    }

    // Remove an item from the order
    public boolean removeItem(MenuItem item) {
        if (itemsOrdered.remove(item)) {
            calculateTotalPrice();
            return true;
        }
        return false;
    }

    // Calculate the total price of the order
    private void calculateTotalPrice() {
        totalPrice = 0.0;
        for (MenuItem item : itemsOrdered) {
            totalPrice += item.getPrice();
        }
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public List<MenuItem> getItemsOrdered() {
        return itemsOrdered;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
