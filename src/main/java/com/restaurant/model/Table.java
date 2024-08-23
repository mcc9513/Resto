package com.restaurant.model;

public class Table {
    private int tableId;
    private String status;  // Open, Seated, Reserved
    private String customerName;

    public Table(int tableId, String status, String customerName) {
        this.tableId = tableId;
        this.status = status;
        this.customerName = customerName;
    }

    // Getters and Setters
    public int getTableId() {
        return tableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
