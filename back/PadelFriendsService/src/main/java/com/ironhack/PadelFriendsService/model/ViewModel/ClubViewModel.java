package com.ironhack.PadelFriendsService.model.ViewModel;

import com.ironhack.PadelFriendsService.model.Entity.Reservation;

import java.util.List;

public class ClubViewModel {

    private String id;

    private String ubication;

    private String name;

    private Integer numberCourts;

    private List<Reservation> reservationList;

    public ClubViewModel(){}

    public ClubViewModel(String id, String ubication, String name, Integer numberCourts, List<Reservation> reservationList) {
        this.id = id;
        this.ubication = ubication;
        this.name = name;
        this.numberCourts = numberCourts;
        this.reservationList = reservationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberCourts() {
        return numberCourts;
    }

    public void setNumberCourts(Integer numberCourts) {
        this.numberCourts = numberCourts;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
