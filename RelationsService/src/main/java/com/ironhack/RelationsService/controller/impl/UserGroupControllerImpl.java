package com.ironhack.RelationsService.controller.impl;

import com.ironhack.RelationsService.controller.interfaces.UserGroupController;
import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserGroupControllerImpl implements UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/usergroups")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findAll() {
        return userGroupService.findAll();
    }

    @GetMapping("/usergroups/group/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup) {
        return userGroupService.findByUserGroupIDUuidGroup(uuidGroup);
    }

    @GetMapping("/usergroups/reservation/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidUser(String uuidUser) {
        return userGroupService.findByUserGroupIDUuidUser(uuidUser);
    }

    @PostMapping("/usergroups")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroup create(UserGroup userGroup) {
        return userGroupService.create(userGroup);
    }

    @DeleteMapping("/usergroups/reservation/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(String uuidUser) {
        userGroupService.deleteUser(uuidUser);
    }

    @DeleteMapping("/usergroups/group/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(String uuidGroup) {
        userGroupService.deleteGroup(uuidGroup);
    }
}
