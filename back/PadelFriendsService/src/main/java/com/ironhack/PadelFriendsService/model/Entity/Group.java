package com.ironhack.PadelFriendsService.model.Entity;

import java.time.LocalDateTime;

public class Group {
    private String id;

    private String name;

    private String description;

    private String image;

    private String city;

    private String province;

    private LocalDateTime createdAt;

    public Group(){}

    public Group(String name, String description, String image, String city, String province) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.city = city;
        this.province = province;
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
}
