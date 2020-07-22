package com.ironhack.PadelFriendsService.model.ViewModel;

import com.ironhack.PadelFriendsService.controller.interfaces.IReservationController;

import java.time.LocalDateTime;

public class ReservationGroupViewModel {
    private String idReservation;
    private String idGroup;
    private String nameGroup;
    private String nameClubReservation;
    private LocalDateTime dateReservation;
    private Boolean isprivate;

    public ReservationGroupViewModel() {}

    public ReservationGroupViewModel(String idReservation, String idGroup, String nameGroup,
                                     String nameClubReservation, LocalDateTime dateReservation, Boolean isprivate) {
        this.idReservation = idReservation;
        this.idGroup = idGroup;
        this.nameGroup = nameGroup;
        this.nameClubReservation = nameClubReservation;
        this.dateReservation = dateReservation;
        this.isprivate = isprivate;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getNameClubReservation() {
        return nameClubReservation;
    }

    public void setNameClubReservation(String nameClubReservation) {
        this.nameClubReservation = nameClubReservation;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Boolean getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(Boolean isprivate) {
        this.isprivate = isprivate;
    }
}
