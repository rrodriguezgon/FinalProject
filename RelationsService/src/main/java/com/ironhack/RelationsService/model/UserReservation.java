package com.ironhack.RelationsService.model;

import javax.persistence.Entity;
import javax.persistence.IdClass;

@Entity
@IdClass(UserReservationID.class)
public class UserReservation {
    private String uuidUser;
    private String uuidReservation;

    public UserReservation(){}

    public UserReservation(String uuidUser, String uuidReservation) {
        this.uuidUser = uuidUser;
        this.uuidReservation = uuidReservation;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public String getUuidReservation() {
        return uuidReservation;
    }

    public void setUuidReservation(String uuidReservation) {
        this.uuidReservation = uuidReservation;
    }
}
