package com.ironhack.RelationsService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GroupReservation {

    @Id
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

