package com.restaurant.model;
import java.time.LocalTime;
import java.time.LocalDate;

public class Reservation {
    private String resName;
    private int numberOfGuests;
    private int tableID;
    private LocalTime time;
    private LocalDate date;

    public Reservation(LocalTime time, LocalDate date, String resName, int numberOfGuests, int tableID) {
        this.time = time;
        this.date = date;
        this.resName = resName;
        this.numberOfGuests = numberOfGuests;
        this.tableID = tableID;
    }

    public Reservation(){};

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



}
