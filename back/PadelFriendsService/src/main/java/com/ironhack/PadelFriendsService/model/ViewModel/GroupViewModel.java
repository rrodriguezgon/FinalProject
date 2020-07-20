package com.ironhack.PadelFriendsService.model.ViewModel;

import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.Entity.UserGroup;

import java.time.LocalDateTime;
import java.util.List;

public class GroupViewModel {

    private String id;

    private String name;

    private String description;

    private String image;

    private LocalDateTime createdAt;

    private List<UserGroup> userGroupList;

    private List<Reservation> reservationList;

    public GroupViewModel(){}

    public GroupViewModel(String id, String name, String description, String image, LocalDateTime createdAt, List<UserGroup> userGroupList, List<Reservation> reservationList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
        this.userGroupList = userGroupList;
        this.reservationList = reservationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserGroup> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        this.userGroupList = userGroupList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
