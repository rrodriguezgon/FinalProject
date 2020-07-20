package com.ironhack.PadelFriendsService.model.Entity;

public class GroupReservationID {
    String uuidGroup;
    String uuidReservation;

    public GroupReservationID(){}

    public GroupReservationID(String uuidGroup, String uuidReservation) {
        this.uuidGroup = uuidGroup;
        this.uuidReservation = uuidReservation;
    }

    public String getUuidGroup() {
        return uuidGroup;
    }

    public void setUuidGroup(String uuidGroup) {
        this.uuidGroup = uuidGroup;
    }

    public String getUuidReservation() {
        return uuidReservation;
    }

    public void setUuidReservation(String uuidReservation) {
        this.uuidReservation = uuidReservation;
    }
}
