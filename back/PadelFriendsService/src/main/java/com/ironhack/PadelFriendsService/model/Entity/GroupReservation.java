package com.ironhack.PadelFriendsService.model.Entity;

public class GroupReservation {

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
