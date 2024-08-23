package com.restaurant.model;

public class Reservation {
    private int id;
    private String customerName;
    private String reservationDate;
    private String reservationTime;

    // Constructor
    public Reservation(int id, String customerName, String reservationDate, String reservationTime) {
        this.id = id;
        this.customerName = customerName;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    // Optional: Override toString(), equals(), and hashCode() methods
}
