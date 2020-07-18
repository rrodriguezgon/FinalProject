package com.ironhack.PadelFriendsService.dto;

import com.ironhack.PadelFriendsService.model.Entity.UserGroup;

import java.util.List;

public class CreateGroupDto {
    private String name;

    private String description;

    private String image;

    private List<UserGroup> userGroupList;

    public CreateGroupDto(){}

    public CreateGroupDto(String name, String description, String image, List<UserGroup> userGroupList) {
        this.name = name;
        this.description = description;
        this.image = image;
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

    public List<UserGroup> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        this.userGroupList = userGroupList;
    }
}
