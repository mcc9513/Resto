package com.restaurant.service;

import com.restaurant.model.Table;
import com.restaurant.model.Order;
import java.util.HashMap;
import java.util.Map;

public class TableService {
    Map<Integer, Table> tables; // Stores tables keyed by table ID

    public TableService() {
        this.tables = new HashMap<>();
        // Initialize with some tables if needed
        initTables();
    }

    private void initTables() {
        // Initialize with predefined tables (for example, 10 tables)
        for (int i = 1; i <= 10; i++) {
            addTable(i, 4);  // Each table with a capacity of 4 for simplicity
        }
    }

    // Method to add a new table
    public void addTable(int tableId, int capacity) {
        if (tables.containsKey(tableId)) {
            throw new IllegalStateException("Table with this ID already exists.");
        }
        tables.put(tableId, new Table(tableId, capacity));
    }

    // Method to assign an order to a table
    public void assignTable(int tableId, Order order) {
        Table table = tables.get(tableId);
        if (table == null) {
            throw new IllegalArgumentException("Table does not exist.");
        }
        table.occupy(order);
    }

    // Method to clear a table for new customers
    public void freeUpTable(int tableId) {
        Table table = tables.get(tableId);
        if (table == null) {
            throw new IllegalArgumentException("Table does not exist.");
        }
        table.freeUp();
    }

    // Update the status of a table
    public void updateTableStatus(int tableId, String status) {
        Table table = tables.get(tableId);
        if (table == null) {
            throw new IllegalArgumentException("Table does not exist.");
        }
        table.setStatus(status);
    }

    // Get a table by its ID
    public Table getTable(int tableId) {
        return tables.get(tableId);
    }

    // Method to check if any tables are available
    public boolean areAnyTablesAvailable() {
        for (Table table : tables.values()) {
            if ("Available".equals(table.getStatus())) {
                return true;
            }
        }
        return false;
    }
}
