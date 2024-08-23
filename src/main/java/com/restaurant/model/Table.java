package com.restaurant.model;

import java.time.LocalTime;

public class Table {
    private final int tableId;
    private final int tableSize;
    private String status;  // Open, Seated, Reserved
    private int partySize;
    private String reservationName;
    private LocalTime reservationTime;

    public Table(int tableId, int tableSize, String status, int partySize, String reservationName, LocalTime reservationTime) {
        this.tableId = tableId;
        this.tableSize = tableSize;
        this.status = status;
        this.partySize = partySize;
        this.reservationName = reservationName;
        this.reservationTime = reservationTime;
    }

    // Getters and Setters
    public int getTableId() {
        return tableId;
    }

    public int getTableSize() {
        return tableSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
