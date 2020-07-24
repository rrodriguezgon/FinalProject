package com.ironhack.PadelFriendsService.model.Entity;

public class UserGroupID {
    private String uuidGroup;
    private String uuidUser;

    public UserGroupID(){}

    public UserGroupID(String uuidGroup, String uuidUser) {
        this.uuidGroup = uuidGroup;
        this.uuidUser = uuidUser;
    }

    public String getUuidGroup() {
        return uuidGroup;
    }

    public void setUuidGroup(String uuidGroup) {
        this.uuidGroup = uuidGroup;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }
}
