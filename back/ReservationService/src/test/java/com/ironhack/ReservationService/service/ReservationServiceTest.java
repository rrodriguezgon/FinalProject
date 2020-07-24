package com.ironhack.ReservationService.service;

import com.ironhack.ReservationService.enums.StatusReservation;
import com.ironhack.ReservationService.model.Reservation;
import com.ironhack.ReservationService.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;


    @BeforeEach
    void setUp() {
        Reservation reservation = new Reservation();
        reservation.setId("1");

        List<Reservation> reservations = Collections.singletonList(reservation);

        when(reservationRepository.findAll()).thenReturn(reservations);
        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.ofNullable(reservation));
        when(reservationRepository.findByClubId(reservation.getId())).thenReturn(reservations);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        doAnswer(i -> {
            return null;
        }).when(reservationRepository).delete(any(Reservation.class));
    }

    @Test
    void findAll() {
        int size = 1;

        assertEquals(size, reservationService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(reservationService.findById("1") instanceof Reservation);
    }

    @Test
    void findByClubId() {
        int size = 1;

        assertEquals(size, reservationService.findByClubId("1").size());
    }

    @Test
    void findByIdNotFound() {
        assertFalse(reservationService.findById("2") instanceof Reservation);
    }

    @Test
    void create() {
        assertTrue(reservationService.create(new Reservation()) instanceof Reservation);
    }

    @Test
    void update() {
        reservationService.update("1",new Reservation());

        assertEquals(1,1);
    }

    @Test
    void delete() {
        reservationService.delete("1");

        assertEquals(1,1);
    }

    @Test
    void getStatusList(){
        int size = 1;

        assertEquals(size, reservationService.getStatusList().size());
    }
}