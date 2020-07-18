package com.ironhack.PadelFriendsService.model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.PadelFriendsService.enums.StatusReservation;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reservation {
    private String id;

    private String clubId;
    private BigDecimal amount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    private Boolean isPrivate;
    private StatusReservation status;
}
