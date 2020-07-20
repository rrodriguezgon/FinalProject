package com.ironhack.PadelFriendsService.model.ViewModel;

import com.ironhack.PadelFriendsService.enums.Role;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;

import java.util.List;

public class UserViewModel {
    private String id;
    private String username;
    private String name;
    private Role role;
    private List<Group> groupList;
    private List<Reservation> reservationList;

    public UserViewModel(){}

    public UserViewModel(String id, String username, String name, Role role, List<Group> groupList, List<Reservation> reservationList) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.groupList = groupList;
        this.reservationList = reservationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
