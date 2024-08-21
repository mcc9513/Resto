package com.restaurant.model;
import com.restaurant.service.ReservationService;

public class Staff extends User {

    private ReservationService reservationService;

    Staff(int id, String passwordHash, String firstNameLastInitial, String role, double hoursWorked) {
        super(id, passwordHash, firstNameLastInitial, role, hoursWorked);
        this.reservationService = reservationService;
    }

    // Method to set a reservation
    public void setReservation(Reservation reservation) {
        reservationService.setReservation(reservation);
    }

    // Method to delete a reservation
    public void deleteReservation(Reservation reservation) {
        reservationService.deleteReservation(reservation);
    }

    // Method to edit a reservation
    public void editReservation(Reservation oldReservation, Reservation newReservation) {
        reservationService.editReservation(oldReservation, newReservation);
    }


}
