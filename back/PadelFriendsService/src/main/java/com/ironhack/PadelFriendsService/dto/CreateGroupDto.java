package com.ironhack.PadelFriendsService.dto;

import com.ironhack.PadelFriendsService.model.Entity.UserGroup;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateGroupDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String image;

    @NotNull
    private String city;

    @NotNull
    private String province;

    private List<UserGroup> userGroupList;

    public CreateGroupDto(){}

    public CreateGroupDto(String name, String description, String image, String city, String province, List<UserGroup> userGroupList) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.city = city;
        this.province = province;
        this.userGroupList = userGroupList;
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

    public List<UserGroup> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        this.userGroupList = userGroupList;
    }
}
