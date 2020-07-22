package com.ironhack.PadelFriendsService.model.ViewModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationListViewModel {
    private String id;
    private String nameClub;
    private int numJug;
    private LocalDateTime date;
    private BigDecimal amount;

    public ReservationListViewModel(){}

    public ReservationListViewModel(String id, String nameClub, int numJug, LocalDateTime date, BigDecimal amount) {
        this.id = id;
        this.nameClub = nameClub;
        this.numJug = numJug;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public int getNumJug() {
        return numJug;
    }

    public void setNumJug(int numJug) {
        this.numJug = numJug;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
