package com.ironhack.GroupService.dto;

public class GroupUpdateDto {
    private String name;

    private String description;

    private String province;

    private String city;

    private String image;

    public GroupUpdateDto(){}

    public GroupUpdateDto(String name, String description, String province, String city, String image) {
        this.name = name;
        this.description = description;
        this.province = province;
        this.city = city;
        this.image = image;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
