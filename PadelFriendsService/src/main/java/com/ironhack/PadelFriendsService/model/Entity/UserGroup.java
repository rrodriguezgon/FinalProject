package com.ironhack.PadelFriendsService.model.Entity;

public class UserGroup {

    private UserGroupID userGroupID;
    private Boolean property;
    private Boolean admin;

    public UserGroup(){}

    public UserGroup(UserGroupID userGroupID, Boolean property, Boolean admin) {
        this.userGroupID = userGroupID;
        this.property = property;
        this.admin = admin;
    }

    public UserGroupID getUserGroupID() {
        return userGroupID;
    }

    public void setUserGroupID(UserGroupID userGroupID) {
        this.userGroupID = userGroupID;
    }

    public Boolean getProperty() {
        return property;
    }

    public void setProperty(Boolean property) {
        this.property = property;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
