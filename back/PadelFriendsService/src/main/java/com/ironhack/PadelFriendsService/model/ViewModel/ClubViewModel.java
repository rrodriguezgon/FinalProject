package com.ironhack.PadelFriendsService.model.ViewModel;

import com.ironhack.PadelFriendsService.model.Entity.Reservation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class ClubViewModel {

    private String id;

    private String ubication;

    private String name;

    private String city;

    private String province;

    private Integer numberCourts;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private List<Reservation> reservationList;

    public ClubViewModel(){}

    public ClubViewModel(String id, String ubication, String name, String city, String province, BigDecimal latitude, BigDecimal longitude, Integer numberCourts, List<Reservation> reservationList) {
        this.id = id;
        this.ubication = ubication;
        this.name = name;
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberCourts = numberCourts;
        this.reservationList = reservationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberCourts() {
        return numberCourts;
    }

    public void setNumberCourts(Integer numberCourts) {
        this.numberCourts = numberCourts;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
