package com.ironhack.PadelFriendsService.controller.interfaces;

import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;

import java.util.List;

public interface IGroupController {

    public List<Group> findAll();

    public GroupViewModel findById(String id);

    public GroupViewModel create(CreateGroupDto createGroupDto);

    public void update(String id, CreateGroupDto createGroupDto);

    public void delete(String id);
}
