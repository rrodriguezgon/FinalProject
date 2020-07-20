package com.ironhack.PadelFriendsService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.PadelFriendsService.enums.StatusReservation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CreateReservationDto {

    @NotNull
    private String clubId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;

    @NotNull
    private Boolean isPrivate;

    @NotNull
    private StatusReservation status;

    private List<String> users;
    private List<String> groups;

    public CreateReservationDto(){}

    public CreateReservationDto(String clubId, BigDecimal amount, LocalDateTime date, Boolean isPrivate,
                                StatusReservation status, List<String> users, List<String> groups) {
        this.clubId = clubId;
        this.amount = amount;
        this.date = date;
        this.isPrivate = isPrivate;
        this.status = status;
        this.users = users;
        this.groups = groups;
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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
