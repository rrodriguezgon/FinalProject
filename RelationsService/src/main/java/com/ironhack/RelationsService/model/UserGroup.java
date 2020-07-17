package com.ironhack.RelationsService.model;

import javax.persistence.Entity;
import javax.persistence.IdClass;

@Entity
@IdClass(UserGroupID.class)
public class UserGroup {

    private String uuidUser;
    private String uuidGroup;
    private Boolean property;
    private Boolean admin;

    public UserGroup(){}

    public UserGroup(String uuidUser, String uuidGroup, Boolean property, Boolean admin) {
        this.uuidUser = uuidUser;
        this.uuidGroup = uuidGroup;
        this.property = property;
        this.admin = admin;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public String getUuidGroup() {
        return uuidGroup;
    }

    public void setUuidGroup(String uuidGroup) {
        this.uuidGroup = uuidGroup;
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
