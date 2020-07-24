package com.ironhack.RelationsService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserReservation {

    @Id
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
