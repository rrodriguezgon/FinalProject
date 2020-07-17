package com.ironhack.RelationsService.model;

import javax.persistence.Entity;
import javax.persistence.IdClass;

@Entity
@IdClass(GroupReservationID.class)
public class GroupReservation {

    private GroupReservationID groupReservationID;

    public GroupReservation(){}

    public GroupReservation(String uuidGroup, String uuidReservation) {
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

