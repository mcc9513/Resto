package com.restaurant.service;

import com.restaurant.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;

public class TableService {
    private List<Table> tables;

    public TableService() {
        this.tables = new ArrayList<>();
        // Prepopulate tables with IDs 1 through 13
        for (int i = 1; i <= 13; i++) {
            tables.add(new Table(i, "Open", null, null, null));
        }
    }

    public List<Table> getAllTables() {
        return tables;
    }

    public void assignCustomer(int tableId, String customerName) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        tableOpt.ifPresent(table -> table.setCustomerName(customerName));
    }

    public void changeTableStatus(int tableId, String newStatus) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        tableOpt.ifPresent(table -> table.setStatus(newStatus));
    }

    public void clearTable(int tableId) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        tableOpt.ifPresent(table -> {
            table.setStatus("Open");
            table.setCustomerName(null);
            table.setDate(null);
            table.setTime(null);
        });
    }

    // Overloaded method to handle reservation date and time
    public void changeTableStatus(int tableId, String newStatus, LocalDate reservationDate, LocalTime reservationTime) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        tableOpt.ifPresent(table -> {
            table.setStatus(newStatus);
            table.setDate(reservationDate);
            table.setTime(reservationTime);
        });
    }
}
