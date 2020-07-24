package com.ironhack.RelationsService.controller.interfaces;

import com.ironhack.RelationsService.model.UserGroup;

import java.util.List;

public interface UserGroupController {

    public List<UserGroup> findAll();

    public List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup);

    public List<UserGroup> findByUserGroupIDUuidUser(String uuidUser);

    public UserGroup create(UserGroup userGroup);

    public void deleteUser(String uuidUser);

    public void deleteGroup(String uuidGroup);
}
