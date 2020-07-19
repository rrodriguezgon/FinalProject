package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.GroupServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupServiceFallbackFunctions serviceFallbackFunctions;

    public List<Group> findAll(){
        return serviceFallbackFunctions.findAllGroups();
    }

    public GroupViewModel findById(String uuidGroup){
        Group group = serviceFallbackFunctions.findByIdGroup(uuidGroup);
        List<Reservation> reservationList = new ArrayList<>();

        List<GroupReservation> groupReservationList = serviceFallbackFunctions.findByGroupReservationIDUuidGroup(uuidGroup);
        List<UserGroup> userGroupList = serviceFallbackFunctions.findByUserGroupIDUuidGroup(uuidGroup);

        for (GroupReservation relationResevation : groupReservationList){
            Reservation reservation = serviceFallbackFunctions.findByIdReservation(relationResevation.getGroupReservationID().getUuidReservation());

            if (reservation != null){
                reservationList.add(reservation);
            }
        }

        return new GroupViewModel(group.getId(),group.getName(),group.getDescription(),
                group.getImage(),group.getCreatedAt(), userGroupList, reservationList);
    }

    public GroupViewModel create(CreateGroupDto createGroupDto){
        Group group = new Group(createGroupDto.getName(),createGroupDto.getDescription(),createGroupDto.getImage());
        List<Reservation> reservationList = new ArrayList<>();
        List<UserGroup> userGroupList = new ArrayList<>();

        Group groupCreated = serviceFallbackFunctions.createGroup(group);

        if ( createGroupDto.getUserGroupList() != null && createGroupDto.getUserGroupList().size() != 0){
            userGroupList = createGroupDto.getUserGroupList();

            createGroupDto.getUserGroupList().forEach(userGroup -> {
                userGroup.getUserGroupID().setUuidGroup(groupCreated.getId());
                serviceFallbackFunctions.createUserGroup(userGroup);
            });
        }

        return new GroupViewModel(groupCreated.getId(), groupCreated.getName(), groupCreated.getDescription(),
                groupCreated.getImage(),groupCreated.getCreatedAt(), userGroupList,reservationList);
    }

    public void update(User user, String uuidGroup, CreateGroupDto createGroupDto) {
        serviceFallbackFunctions.updateGroup(uuidGroup, createGroupDto);

        serviceFallbackFunctions.deleteUserGroupGroup(uuidGroup);

        if (createGroupDto.getUserGroupList().size() != 0){
            createGroupDto.getUserGroupList().forEach(userGroup -> {
                userGroup.getUserGroupID().setUuidGroup(uuidGroup);
                serviceFallbackFunctions.createUserGroup(userGroup);
            });
        }
    }

    public void delete(String uuidGroup){
        serviceFallbackFunctions.deleteGroup(uuidGroup);
        serviceFallbackFunctions.deleteUserGroupGroup(uuidGroup);
    }
}
