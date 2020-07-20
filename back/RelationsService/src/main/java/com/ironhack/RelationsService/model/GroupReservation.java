package com.ironhack.RelationsService.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class GroupReservation {

    @EmbeddedId
    private GroupReservationID groupReservationID;

    public GroupReservation(){}

    public GroupReservation(GroupReservationID groupReservationID) {
        this.groupReservationID = groupReservationID;
    }

    public GroupReservationID getGroupReservationID() {
        return groupReservationID;
    }

    public void setGroupReservationID(GroupReservationID groupReservationID) {
        this.groupReservationID = groupReservationID;
    }
}

