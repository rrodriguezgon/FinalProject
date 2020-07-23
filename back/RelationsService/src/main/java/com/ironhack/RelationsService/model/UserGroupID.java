package com.ironhack.RelationsService.model;

import java.io.Serializable;

public class UserGroupID  implements Serializable {
    String uuidGroup;
    String uuidUser;

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
