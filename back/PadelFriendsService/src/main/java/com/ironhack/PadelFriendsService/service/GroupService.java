package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.GroupServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationGroupViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.UserGroupViewModel;
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

        List<UserGroupViewModel> userGroupViewModelList = new ArrayList<>();
        List<ReservationGroupViewModel> reservationList = new ArrayList<>();

        List<GroupReservation> groupReservationList = serviceFallbackFunctions.findByGroupReservationIDUuidGroup(uuidGroup);
        List<UserGroup> userGroupList = serviceFallbackFunctions.findByUserGroupIDUuidGroup(uuidGroup);

        for (GroupReservation relationResevation : groupReservationList){
            Reservation reservation = serviceFallbackFunctions.findByIdReservation(relationResevation.getGroupReservationID().getUuidReservation());

            if (reservation != null){
                Club club = serviceFallbackFunctions.findClubById(reservation.getClubId());
                String nameClub = "Club not Found.";
                if (club == null){
                    DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. ClubId: " + reservation.getClubId());

                    LOGGER.warn(ex);
                } else {
                    nameClub = club.getName();
                }

                reservationList.add(new ReservationGroupViewModel(reservation.getId(), group.getId(), group.getName(), nameClub,
                        reservation.getDate(),reservation.getPrivate()));
            }  else {
                DataNotFoundException ex = new DataNotFoundException("This Reservation does not exist so it cannot be added to the Group. ReservationId:" + relationResevation.getGroupReservationID().getUuidReservation());

                LOGGER.warn(ex);
            }
        }

        for (UserGroup userGroup : userGroupList){
            User user = serviceFallbackFunctions.findUserById(userGroup.getUserGroupID().getUuidUser());

            if (user != null){
                userGroupViewModelList.add(new UserGroupViewModel(user.getId(),group.getId(),user.getName(),group.getName(), userGroup.getProperty(), userGroup.getAdmin()));
            }  else {
                DataNotFoundException ex = new DataNotFoundException("This User does not exist so it cannot be added to the Group. ReservationId:" + userGroup.getUserGroupID().getUuidUser());

                LOGGER.warn(ex);
            }
        }

        return new GroupViewModel(group.getId(),group.getName(),group.getDescription(),
                group.getImage(), group.getCity(), group.getProvince(),group.getCreatedAt(), userGroupViewModelList, reservationList);
    }

    public GroupViewModel create(CreateGroupDto createGroupDto){
        Group group = new Group(createGroupDto.getName(),createGroupDto.getDescription(),createGroupDto.getImage(), createGroupDto.getCity(),createGroupDto.getProvince());
        List<ReservationGroupViewModel> reservationList = new ArrayList<>();
        List<UserGroupViewModel> userGroupViewModelList = new ArrayList<>();

        Group groupCreated = serviceFallbackFunctions.createGroup(createGroupDto);

        if ( createGroupDto.getUserGroupList() != null && createGroupDto.getUserGroupList().size() != 0){
            createGroupDto.getUserGroupList().forEach(userGroup -> {
                User userFound = serviceFallbackFunctions.findUserById(userGroup.getUserGroupID().getUuidUser());

                if (userFound != null) {
                    userGroup.getUserGroupID().setUuidGroup(groupCreated.getId());
                    serviceFallbackFunctions.createUserGroup(userGroup);
                    userGroupViewModelList.add(new UserGroupViewModel(userFound.getId(),group.getId(),userFound.getName(),group.getName(), userGroup.getProperty(), userGroup.getAdmin()));

                } else {
                    DataNotFoundException ex = new DataNotFoundException("This user does not exist so it cannot be added to the Group. UserId: " + userGroup.getUserGroupID().getUuidUser());
                    LOGGER.warn(ex);
                }
            });
        }

        return new GroupViewModel(groupCreated.getId(), groupCreated.getName(), groupCreated.getDescription(),
                groupCreated.getImage(), groupCreated.getCity(), groupCreated.getProvince(), groupCreated.getCreatedAt(), userGroupViewModelList,reservationList);
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
