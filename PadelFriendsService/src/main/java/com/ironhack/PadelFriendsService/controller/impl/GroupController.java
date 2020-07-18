package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IGroupController;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;
import com.ironhack.PadelFriendsService.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController implements IGroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupViewModel findById(@PathVariable("id") String id) {
        return groupService.findById(id);
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public GroupViewModel create(@RequestBody @Valid CreateGroupDto createGroupDto) {
        return groupService.create(createGroupDto);
    }

    @PutMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,@RequestBody @Valid CreateGroupDto createGroupDto) {
        groupService.update(id,createGroupDto);
    }

    @DeleteMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        groupService.delete(id);
    }
}
