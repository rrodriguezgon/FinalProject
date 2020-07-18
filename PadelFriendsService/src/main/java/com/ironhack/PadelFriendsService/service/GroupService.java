package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.client.GroupClient;
import com.ironhack.PadelFriendsService.client.RelationsClient;
import com.ironhack.PadelFriendsService.client.ReservationClient;
import com.ironhack.PadelFriendsService.client.UserClient;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.GroupViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupClient groupClient;

    @Autowired
    private RelationsClient relationsClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ReservationClient reservationClient;

    public List<Group> findAll(){
        return groupClient.findAll();
    }

    public GroupViewModel findById(String uuidGroup){
        Group group = groupClient.findById(uuidGroup);
        List<Reservation> reservationList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<GroupReservation> groupReservationList = relationsClient.findByGroupReservationIDUuidGroup(uuidGroup);
        List<UserGroup> userGroupList = relationsClient.findByUserGroupIDUuidGroup(uuidGroup);

        for (GroupReservation relationResevation : groupReservationList){
            Reservation reservation = reservationClient.findById(relationResevation.getGroupReservationID().getUuidReservation());

            reservationList.add(reservation);
        }

        return new GroupViewModel(group.getId(),group.getName(),group.getDescription(),
                group.getImage(),group.getCreatedAt(), userGroupList, reservationList);
    }

    public GroupViewModel create(CreateGroupDto createGroupDto){
        Group group = new Group(createGroupDto.getName(),createGroupDto.getDescription(),createGroupDto.getImage(), LocalDateTime.now());
        List<Reservation> reservationList = new ArrayList<>();
        List<UserGroup> userGroupList = new ArrayList<>();

        Group groupCreated = groupClient.create(group);

        if ( createGroupDto.getUserGroupList() != null && createGroupDto.getUserGroupList().size() != 0){
            userGroupList = createGroupDto.getUserGroupList();

            createGroupDto.getUserGroupList().forEach(userGroup -> {
                userGroup.getUserGroupID().setUuidGroup(groupCreated.getId());
                relationsClient.createUserGroup(userGroup);
            });
        }

        return new GroupViewModel(groupCreated.getId(), groupCreated.getName(), groupCreated.getDescription(),
                groupCreated.getImage(),groupCreated.getCreatedAt(), userGroupList,reservationList);
    }

    public void update(String uuidGroup, CreateGroupDto createGroupDto) {
        Group groupFound = groupClient.findById(uuidGroup);
        groupFound.setName(createGroupDto.getName());
        groupFound.setDescription(createGroupDto.getDescription());
        groupFound.setImage(createGroupDto.getImage());

        relationsClient.deleteUserGroupGroup(uuidGroup);

        if (createGroupDto.getUserGroupList().size() != 0){
            createGroupDto.getUserGroupList().forEach(userGroup -> {
                relationsClient.createUserGroup(userGroup);
            });
        }
    }

    public void delete(String uuidGroup){
        groupClient.delete(uuidGroup);
        relationsClient.deleteUserGroupGroup(uuidGroup);
    }
}
