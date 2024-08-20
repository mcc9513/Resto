package com.restaurant.model;

public class Table {
    private int tableId;
    private int capacity;
    private String status;
    private Order currentOrder;

    // Constructor
    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.status = "Available";
        this.currentOrder = null;
    }
    // Blank Constructor For Test or Edge Cases
    public Table() {

    }

    // Method to occupy the table with an order
    public void occupy(Order order) {
        if (!"Occupied".equals(status)) {
            this.currentOrder = order;
            status = "Occupied";
        } else {
            System.out.println("Table is already occupied.");
        }
    }

    // Method to free up the table
    public void freeUp() {
        if (!"Available".equals(status)) {
            status = "Available";
            this.currentOrder = null; // Clearing the current order
        } else {
            System.out.println("Table is already free.");
        }
    }

    // Getter and Setters

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
