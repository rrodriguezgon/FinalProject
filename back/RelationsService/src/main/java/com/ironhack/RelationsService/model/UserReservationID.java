package com.ironhack.RelationsService.model;

import java.io.Serializable;

public class UserReservationID implements Serializable {
    String uuidReservation;
    String uuidUser;

    public UserReservationID(){}

    public UserReservationID(String uuidReservation, String uuidUser) {
        this.uuidReservation = uuidReservation;
        this.uuidUser = uuidUser;
    }

    public String getUuidReservation() {
        return uuidReservation;
    }

    public void setUuidReservation(String uuidReservation) {
        this.uuidReservation = uuidReservation;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }
}
