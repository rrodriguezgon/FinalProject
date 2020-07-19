package com.ironhack.PadelFriendsService.dto;

import com.ironhack.PadelFriendsService.model.Entity.UserGroup;

import java.util.List;

public class GroupUpdateDto {
    private String name;

    private String description;

    private String image;

    private List<UserGroup> userGroupList;

    public GroupUpdateDto(){}

    public GroupUpdateDto(String name, String description, String image) {
        this.name = name;
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
