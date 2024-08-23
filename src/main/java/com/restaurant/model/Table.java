package com.restaurant.model;
import java.time.LocalDate;
import java.time.LocalTime;


public class Table {
    private int tableId;
    private String status;  // Open, Seated, Reserved
    private String customerName;
    private LocalDate date;
    private LocalTime time;

    public Table(int tableId, String status, String customerName, LocalDate date, LocalTime time) {
        this.tableId = tableId;
        this.status = status;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
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
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

}
