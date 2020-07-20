package com.ironhack.ClubService.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Club {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String id;

    private String ubication;

    private String name;

    private Integer numberCourts;

    public Club(){}

    public Club(String ubication, String name, Integer numberCourts) {
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
