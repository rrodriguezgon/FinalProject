package com.ironhack.PadelFriendsService.model.Entity;

public class Club {
    private String id;

    private String ubication;

    private String name;

    private Integer numberCourts;

    public Club(){}

    public Club(String id, String ubication, String name, Integer numberCourts) {
        this.id = id;
        this.ubication = ubication;
        this.name = name;
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

    public Integer getNumberCourts() {
        return numberCourts;
    }

    public void setNumberCourts(Integer numberCourts) {
        this.numberCourts = numberCourts;
    }
}