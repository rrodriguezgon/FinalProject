package com.ironhack.RelationsService.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
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
