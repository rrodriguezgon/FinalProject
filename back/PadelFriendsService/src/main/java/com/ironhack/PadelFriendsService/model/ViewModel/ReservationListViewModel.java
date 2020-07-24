package com.ironhack.PadelFriendsService.model.ViewModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationListViewModel {
    private String id;
    private String nameClub;
    private String provinceClub;
    private String cityClub;
    private int numJug;
    private LocalDateTime date;
    private BigDecimal amount;
    private Boolean isprivate;

    public ReservationListViewModel(){}

    public ReservationListViewModel(String id, String nameClub, String provinceClub, String cityClub, int numJug, LocalDateTime date, BigDecimal amount, Boolean isprivate) {
        this.id = id;
        this.nameClub = nameClub;
        this.provinceClub = provinceClub;
        this.cityClub = cityClub;
        this.numJug = numJug;
        this.date = date;
        this.amount = amount;
        this.isprivate = isprivate;
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

    public String getProvinceClub() {
        return provinceClub;
    }

    public void setProvinceClub(String provinceClub) {
        this.provinceClub = provinceClub;
    }

    public String getCityClub() {
        return cityClub;
    }

    public void setCityClub(String cityClub) {
        this.cityClub = cityClub;
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

    public Boolean getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(Boolean isprivate) {
        this.isprivate = isprivate;
    }
}
