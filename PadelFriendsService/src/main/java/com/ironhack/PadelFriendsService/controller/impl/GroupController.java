package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IGroupController;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import com.ironhack.PadelFriendsService.model.Entity.User;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;
import com.ironhack.PadelFriendsService.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Group Controller")
@RestController
public class GroupController implements IGroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    @ApiOperation(value="Get All Groups",
            notes = "Display all Groups",
            response = Group.class, responseContainer = "List")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/groups/{id}")
    @ApiOperation(value="Get Details Group",
            notes = "Display Details Group",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public GroupViewModel findById(@PathVariable("id") String id) {
        return groupService.findById(id);
    }

    @PostMapping("/groups")
    @ApiOperation(value="Create new Group",
            notes = "Display Group Created",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public GroupViewModel create(@RequestBody @Valid CreateGroupDto createGroupDto) {
        return groupService.create(createGroupDto);
    }

    @PutMapping("/groups/{id}")
    @ApiOperation(value="Update Group",
            notes = "Display Group Updated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal User user, @PathVariable("id") String id, @RequestBody @Valid CreateGroupDto createGroupDto) {
        groupService.update(user, id,createGroupDto);
    }

    @DeleteMapping("/groups/{id}")
    @ApiOperation(value="Delete Group",
            notes = "Delete Group")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        groupService.delete(id);
    }
}
