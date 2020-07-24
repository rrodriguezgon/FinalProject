package com.ironhack.PadelFriendsService.model.Entity;

public class UserReservationID {
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
