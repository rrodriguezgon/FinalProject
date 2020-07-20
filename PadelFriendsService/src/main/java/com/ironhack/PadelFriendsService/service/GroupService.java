package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.GroupServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private static final Logger LOGGER = LogManager.getLogger(GroupService.class);

    @Autowired
    private GroupServiceFallbackFunctions serviceFallbackFunctions;

    public List<Group> findAll(){
        return serviceFallbackFunctions.findAllGroups();
    }

    public GroupViewModel findById(String uuidGroup){
        Group group = serviceFallbackFunctions.findByIdGroup(uuidGroup);

        if (group == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Group not exists. GroupId: " + uuidGroup);

            LOGGER.error(ex);
            throw ex;
        }

        List<Reservation> reservationList = new ArrayList<>();

        List<GroupReservation> groupReservationList = serviceFallbackFunctions.findByGroupReservationIDUuidGroup(uuidGroup);
        List<UserGroup> userGroupList = serviceFallbackFunctions.findByUserGroupIDUuidGroup(uuidGroup);

        for (GroupReservation relationResevation : groupReservationList){
            Reservation reservation = serviceFallbackFunctions.findByIdReservation(relationResevation.getGroupReservationID().getUuidReservation());

            if (reservation != null){
                reservationList.add(reservation);
            }  else {
                DataNotFoundException ex = new DataNotFoundException("This Reservation does not exist so it cannot be added to the reservation. ReservationId:" + relationResevation.getGroupReservationID().getUuidReservation());

                LOGGER.warn(ex);
            }
        }

        return new GroupViewModel(group.getId(),group.getName(),group.getDescription(),
                group.getImage(),group.getCreatedAt(), userGroupList, reservationList);
    }

    public GroupViewModel create(CreateGroupDto createGroupDto){
        Group group = new Group(createGroupDto.getName(),createGroupDto.getDescription(),createGroupDto.getImage());
        List<Reservation> reservationList = new ArrayList<>();
        List<UserGroup> userGroupList = new ArrayList<>();

        Group groupCreated = serviceFallbackFunctions.createGroup(createGroupDto);

        if ( createGroupDto.getUserGroupList() != null && createGroupDto.getUserGroupList().size() != 0){
            createGroupDto.getUserGroupList().forEach(userGroup -> {
                User userFound = serviceFallbackFunctions.findUserById(userGroup.getUserGroupID().getUuidUser());

                if (userFound != null) {
                    userGroup.getUserGroupID().setUuidGroup(groupCreated.getId());
                    serviceFallbackFunctions.createUserGroup(userGroup);
                    userGroupList.add(userGroup);

                } else {
                    DataNotFoundException ex = new DataNotFoundException("This user does not exist so it cannot be added to the Group. UserId: " + userGroup.getUserGroupID().getUuidUser());
                    LOGGER.warn(ex);
                }
            });
        }

        return new GroupViewModel(groupCreated.getId(), groupCreated.getName(), groupCreated.getDescription(),
                groupCreated.getImage(),groupCreated.getCreatedAt(), userGroupList,reservationList);
    }

    public void update(User user, String uuidGroup, CreateGroupDto createGroupDto) {
        Group group = serviceFallbackFunctions.findByIdGroup(uuidGroup);

        if (group == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Group not exists. GroupId: " + uuidGroup);

            LOGGER.error(ex);
            throw ex;
        }

        serviceFallbackFunctions.updateGroup(uuidGroup, createGroupDto);

        serviceFallbackFunctions.deleteUserGroupGroup(uuidGroup);

        if (createGroupDto.getUserGroupList().size() != 0){
            createGroupDto.getUserGroupList().forEach(userGroup -> {
                User userFound = serviceFallbackFunctions.findUserById(userGroup.getUserGroupID().getUuidUser());

                if (userFound != null) {
                    userGroup.getUserGroupID().setUuidGroup(uuidGroup);
                    serviceFallbackFunctions.createUserGroup(userGroup);

                } else {
                    DataNotFoundException ex = new DataNotFoundException("This user does not exist so it cannot be added to the Group. UserId: " + userGroup.getUserGroupID().getUuidUser());
                    LOGGER.warn(ex);
                }
            });
        }
    }

    public void delete(String uuidGroup){
        Group group = serviceFallbackFunctions.findByIdGroup(uuidGroup);

        if (group == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Group not exists. GroupId: " + uuidGroup);

            LOGGER.error(ex);
            throw ex;
        }

        serviceFallbackFunctions.deleteGroup(uuidGroup);
        serviceFallbackFunctions.deleteUserGroupGroup(uuidGroup);
    }
}
