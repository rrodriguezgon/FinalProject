package com.ironhack.PadelFriendsService.model.ViewModel;

public class UserGroupViewModel {
    private String userId;
    private String groupId;
    private String nameUser;
    private String nameGroup;
    private Boolean property;
    private Boolean admin;

    public UserGroupViewModel() {}

    public UserGroupViewModel(String userId, String groupId, String nameUser, String nameGroup, Boolean property, Boolean admin) {
        this.userId = userId;
        this.groupId = groupId;
        this.nameUser = nameUser;
        this.nameGroup = nameGroup;
        this.property = property;
        this.admin = admin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
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
