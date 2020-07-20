package com.ironhack.PadelFriendsService.model.ViewModel;

import com.ironhack.PadelFriendsService.enums.StatusReservation;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.Entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationViewModel {

    private String id;
    private Club club;
    private BigDecimal amount;
    private LocalDateTime date;
    private Boolean isPrivate;
    private StatusReservation status;
    private List<User> userList;
    private List<Group> groupList;

    public ReservationViewModel(){}

    public ReservationViewModel(String id, Club club, BigDecimal amount, LocalDateTime date, Boolean isPrivate, StatusReservation status, List<User> userList, List<Group> groupList) {
        this.id = id;
        this.club = club;
        this.amount = amount;
        this.date = date;
        this.isPrivate = isPrivate;
        this.status = status;
        this.userList = userList;
        this.groupList = groupList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
        this.status = status;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
