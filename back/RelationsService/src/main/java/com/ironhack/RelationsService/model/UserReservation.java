package com.ironhack.RelationsService.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserReservation {

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
