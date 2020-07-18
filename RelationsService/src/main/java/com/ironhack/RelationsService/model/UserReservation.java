package com.ironhack.RelationsService.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserReservation {

    @EmbeddedId
    private UserReservationID userReservationID;

    public UserReservation(){}

    public UserReservation(UserReservationID userReservationID) {
        this.userReservationID = userReservationID;
    }

    public UserReservationID getUserReservationID() {
        return userReservationID;
    }

    public void setUserReservationID(UserReservationID userReservationID) {
        this.userReservationID = userReservationID;
    }
}
