package com.ironhack.RelationsService.controller.impl;

import com.ironhack.RelationsService.controller.interfaces.UserGroupController;
import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/usergroups/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidGroup(@PathVariable("id") String uuidGroup) {
        return userGroupService.findByUserGroupIDUuidGroup(uuidGroup);
    }

    @GetMapping("/usergroups/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidUser(@PathVariable("id") String uuidUser) {
        return userGroupService.findByUserGroupIDUuidUser(uuidUser);
    }

    @PostMapping("/usergroups")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroup create(@RequestBody @Valid UserGroup userGroup) {
        return userGroupService.create(userGroup);
    }

    @DeleteMapping("/usergroups/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String uuidUser) {
        userGroupService.deleteUser(uuidUser);
    }

    @DeleteMapping("/usergroups/group/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable("id") String uuidGroup) {
        userGroupService.deleteGroup(uuidGroup);
    }
}
