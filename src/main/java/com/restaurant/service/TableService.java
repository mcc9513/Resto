package com.restaurant.service;

import com.restaurant.model.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;

public class TableService {
    private List<Table> tables;

    public TableService() {
        this.tables = new ArrayList<>();
        // Prepopulate tables with IDs 1 through 13 and assign table sizes
        for (int i = 1; i <= 13; i++) {
            int tableSize = (i <= 6) ? 4 : 6;
            tables.add(new Table(i, tableSize, "Open", 0, null, null));
        }
    }

    public List<Table> getAllTables() {
        return tables;
    }

    public boolean changeTableStatusToReserved(int tableId, int partySize, String reservationName, LocalTime reservationTime) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        if (tableOpt.isPresent()) {
            Table table = tableOpt.get();
            if (partySize > table.getTableSize()) {
                return false; // Party size exceeds table size
            }
            table.setStatus("Reserved");
            table.setPartySize(partySize);
            table.setReservationName(reservationName);
            table.setReservationTime(reservationTime);
            updateCSVFile(table);
            table.setStatus("Open"); // Revert status to Open after updating the reservation
            return true;
        }
        return false;
    }

    public boolean changeTableStatusToSeated(int tableId, int partySize) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        if (tableOpt.isPresent()) {
            Table table = tableOpt.get();
            if (partySize > table.getTableSize()) {
                return false; // Party size exceeds table size
            }
            table.setStatus("Seated");
            table.setPartySize(partySize);
            // Do not revert the status; it remains as "Seated"
            return true;
        }
        return false;
    }

    public void clearTable(int tableId) {
        Optional<Table> tableOpt = tables.stream().filter(t -> t.getTableId() == tableId).findFirst();
        tableOpt.ifPresent(table -> {
            table.setStatus("Open");
            table.setPartySize(0);
            table.setReservationName(null);
            table.setReservationTime(null);
        });
    }

    private void updateCSVFile(Table table) {
        try (FileWriter csvWriter = new FileWriter("reservations.csv", true)) {
            csvWriter.append(String.join(",",
                    String.valueOf(table.getTableId()),
                    String.valueOf(table.getPartySize()),
                    table.getReservationName() != null ? table.getReservationName() : "",
                    table.getReservationTime() != null ? table.getReservationTime().toString() : ""
            ));
            csvWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
