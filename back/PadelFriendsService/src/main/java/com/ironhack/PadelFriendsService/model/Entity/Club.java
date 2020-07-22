package com.ironhack.PadelFriendsService.model.Entity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Club {
    private String id;

    @NotNull
    private String ubication;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String province;

    @NotNull
    private Integer numberCourts;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    public Club(){}

    public Club(String id, String ubication, String name, String city, String province, BigDecimal latitude, BigDecimal longitude, Integer numberCourts) {
        this.id = id;
        this.ubication = ubication;
        this.name = name;
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberCourts = numberCourts;
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

    public Integer getNumberCourts() {
        return numberCourts;
    }

    public void setNumberCourts(Integer numberCourts) {
        this.numberCourts = numberCourts;
    }
}
