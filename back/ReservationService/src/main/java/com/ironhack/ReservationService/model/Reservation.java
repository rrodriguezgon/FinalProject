package com.ironhack.ReservationService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.ReservationService.enums.StatusReservation;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String id;

    @NotBlank
    private String clubId;
    private BigDecimal amount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    private Boolean isPrivate;
    private StatusReservation status;

    public Reservation(){}

    public Reservation(String clubId, BigDecimal amount, LocalDateTime date, Boolean isPrivate, StatusReservation status) {
        this.clubId = clubId;
        this.amount = amount;
        this.date = date;
        this.isPrivate = isPrivate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
        this.status = status;
    }
}
