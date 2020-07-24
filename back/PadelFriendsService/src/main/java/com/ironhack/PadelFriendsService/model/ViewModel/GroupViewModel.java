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

    private String city;

    private String province;

    private LocalDateTime createdAt;

    private List<UserGroupViewModel> userGroupList;

    private List<ReservationGroupViewModel> reservationList;

    public GroupViewModel(){}

    public GroupViewModel(String id, String name, String description, String image, String city, String province, LocalDateTime createdAt,
                          List<UserGroupViewModel> userGroupList, List<ReservationGroupViewModel> reservationList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.city = city;
        this.province = province;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserGroupViewModel> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroupViewModel> userGroupList) {
        this.userGroupList = userGroupList;
    }

    public List<ReservationGroupViewModel> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationGroupViewModel> reservationList) {
        this.reservationList = reservationList;
    }
}
