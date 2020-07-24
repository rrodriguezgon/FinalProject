package com.ironhack.ReservationService.model;

import com.ironhack.ReservationService.enums.StatusReservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    @BeforeEach
    void setUp() {
        reservation = new Reservation();
    }

    @Test
    void getDate() {
        LocalDateTime param = LocalDateTime.now();
        reservation.setDate(param);

        assertEquals(param, reservation.getDate());
    }

    @Test
    void getPrivate() {
        Boolean param = false;
        reservation.setPrivate(param);

        assertEquals(param, reservation.getPrivate());
    }

    @Test
    void getStatus() {
        StatusReservation param = StatusReservation.CLOSED;
        reservation.setStatus(param);

        assertEquals(param, reservation.getStatus());
    }
}