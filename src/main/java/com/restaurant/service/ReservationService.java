package com.restaurant.service;

import com.restaurant.model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    // In-memory list to store reservations
    private List<Reservation> reservations;

    // Constructor
    public ReservationService() {
        this.reservations = new ArrayList<>();
    }

    // Method to set a reservation
    public void setReservation(Reservation reservation) {
        // Add the reservation to the list
        reservations.add(reservation);
        System.out.println("Reservation set for " + reservation.getResName() + " on " + reservation.getDate() + " at " + reservation.getTime());
    }

    // Method to delete a reservation
    public void deleteReservation(Reservation reservation) {
        // Remove the reservation from the list
        if (reservations.remove(reservation)) {
            System.out.println("Reservation for " + reservation.getResName() + " has been deleted.");
        } else {
            System.out.println("Reservation not found for " + reservation.getResName() + ".");
        }
    }

    // Method to edit a reservation
    public void editReservation(Reservation oldReservation, Reservation newReservation) {
        int index = reservations.indexOf(oldReservation);
        if (index != -1) {
            // Replace the old reservation with the new one
            reservations.set(index, newReservation);
            System.out.println("Reservation for " + newReservation.getResName() + " has been updated.");
        } else {
            System.out.println("Reservation not found for " + oldReservation.getResName() + ".");
        }
    }

    // Additional helper method to find a reservation by name (optional)
    public Reservation findReservationByName(String resName) {
        for (Reservation reservation : reservations) {
            if (reservation.getResName().equals(resName)) {
                return reservation;
            }
        }
        return null;
    }
}
