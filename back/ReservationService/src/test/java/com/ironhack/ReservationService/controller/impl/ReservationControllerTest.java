package com.ironhack.ReservationService.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.ReservationService.enums.StatusReservation;
import com.ironhack.ReservationService.model.Reservation;
import com.ironhack.ReservationService.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ReservationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        Reservation reservation = new Reservation();
        reservation.setId("1");

        List<Reservation> reservations = Collections.singletonList(reservation);

        when(reservationService.findAll()).thenReturn(reservations);
        when(reservationService.findById(reservation.getId())).thenReturn(reservation);
        when(reservationService.findByClubId(reservation.getId())).thenReturn(reservations);
        when(reservationService.create(any(Reservation.class))).thenReturn(reservation);
        doAnswer(i -> {
            return null;
        }).when(reservationService).update(eq("1"),any(Reservation.class));
        doAnswer(i -> {
            return null;
        }).when(reservationService).delete("1");
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/reservations"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/reservations/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findByClubId() throws Exception {
        mockMvc.perform(get("/reservations/club/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/reservations")
                .content(objectMapper.writeValueAsString(new Reservation("1",new BigDecimal("1"), LocalDateTime.now(), true, StatusReservation.CLOSED)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/reservations/1")
                .content(objectMapper.writeValueAsString(new Reservation("1",new BigDecimal("1"), LocalDateTime.now(), true, StatusReservation.CLOSED)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteClub() throws Exception {
        mockMvc.perform(delete("/reservations/1"))
                .andExpect(status().isNoContent());
    }
}