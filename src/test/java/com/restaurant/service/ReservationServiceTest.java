package com.restaurant.service;

import com.restaurant.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Reservation createReservation() {
        return new Reservation(1, "John Doe", "2024-08-23", "18:00");
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = createReservation();

        when(reservationService.createReservation(any(Reservation.class))).thenReturn(true);

        boolean result = reservationService.createReservation(reservation);
        assertEquals(true, result);
        verify(reservationService).createReservation(any(Reservation.class));
    }

    @Test
    void testUpdateReservation() {
        Reservation reservation = createReservation();

        when(reservationService.updateReservation(any(Reservation.class))).thenReturn(true);

        boolean result = reservationService.updateReservation(reservation);
        assertEquals(true, result);
        verify(reservationService).updateReservation(any(Reservation.class));
    }

    @Test
    void testCancelReservation() {
        int reservationId = 1;

        when(reservationService.cancelReservation(anyInt())).thenReturn(true);

        boolean result = reservationService.cancelReservation(reservationId);
        assertEquals(true, result);
        verify(reservationService).cancelReservation(anyInt());
    }

    @Test
    void testGetReservation() {
        int reservationId = 1;
        Reservation reservation = createReservation();

        when(reservationService.getReservation(anyInt())).thenReturn(reservation);

        Reservation result = reservationService.getReservation(reservationId);
        assertEquals(reservation, result);
        verify(reservationService).getReservation(anyInt());
    }
}
