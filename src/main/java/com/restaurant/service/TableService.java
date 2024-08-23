package com.restaurant.service;

import com.restaurant.model.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableService {
    private Map<Integer, Table> tables; // Stores tables keyed by table ID

    public TableService() {
        this.tables = new HashMap<>();
        // Initialize with 13 tables by default
        initTables();
    }

    // Initialize 13 tables with default "Open" status and no customer
    private void initTables() {
        for (int i = 1; i <= 13; i++) {
            tables.put(i, new Table(i, "Open", null));  // Table ID, Status, Customer Name
        }
    }

    // Get all tables
    public List<Table> getAllTables() {
        return tables.values().stream().collect(Collectors.toList());
    }

    // Assign a customer name to the table
    public void assignCustomer(int tableId, String customerName) {
        Table table = tables.get(tableId);
        if (table == null) {
            throw new IllegalArgumentException("Table does not exist.");
        }
        table.setCustomerName(customerName);  // Assign customer name
        table.setStatus("Seated");  // Change status to "Seated"
    }

    // Change the status of a table
    public void changeTableStatus(int tableId, String status) {
        Table table = tables.get(tableId);
        if (table == null) {
            throw new IllegalArgumentException("Table does not exist.");
        }
        table.setStatus(status);  // Update status (Open, Reserved, etc.)
    }

    // Clear the table (set status to "Open" and remove the customer name)
    public void clearTable(int tableId) {
        Table table = tables.get(tableId);
        if (table == null) {
            throw new IllegalArgumentException("Table does not exist.");
        }
        table.setStatus("Open");  // Reset status to "Open"
        table.setCustomerName(null);  // Remove the customer name
    }
}

