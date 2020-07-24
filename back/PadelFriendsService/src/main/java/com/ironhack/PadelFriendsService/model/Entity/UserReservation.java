package com.ironhack.PadelFriendsService.model.Entity;

public class UserReservation {
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
