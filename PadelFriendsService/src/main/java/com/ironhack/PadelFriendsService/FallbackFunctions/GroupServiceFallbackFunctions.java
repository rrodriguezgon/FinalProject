package com.ironhack.PadelFriendsService.FallbackFunctions;

import com.ironhack.PadelFriendsService.client.GroupClient;
import com.ironhack.PadelFriendsService.client.RelationsClient;
import com.ironhack.PadelFriendsService.client.ReservationClient;
import com.ironhack.PadelFriendsService.client.UserClient;
import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.exceptions.ServiceNotAccessibleException;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import com.ironhack.PadelFriendsService.model.Entity.GroupReservation;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.Entity.UserGroup;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupServiceFallbackFunctions {

    @Autowired
    private GroupClient groupClient;

    @Autowired
    private RelationsClient relationsClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ReservationClient reservationClient;

    @HystrixCommand(fallbackMethod = "findAllGroupsFail")
    public List<Group> findAllGroups(){
        return groupClient.findAll();
    }

    @HystrixCommand(fallbackMethod = "findByIdGroupFail")
    public Group findByIdGroup(String uuidGroup){
        return groupClient.findById(uuidGroup);
    }

    @HystrixCommand(fallbackMethod = "findByGroupReservationIDUuidGroupFail")
    public List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup){
        return relationsClient.findByGroupReservationIDUuidGroup(uuidGroup);
    }

    @HystrixCommand(fallbackMethod = "findByUserGroupIDUuidGroupFail")
    public List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup){
        return relationsClient.findByUserGroupIDUuidGroup(uuidGroup);
    }

    @HystrixCommand(fallbackMethod = "findByIdReservationFail")
    public Reservation findByIdReservation(String uuidReservation){
        return reservationClient.findById(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "createGroupFail")
    public Group createGroup(CreateGroupDto group){
        return groupClient.create(group);
    }

    @HystrixCommand(fallbackMethod = "createUserGroupFail")
    public void createUserGroup(UserGroup userGroup){
        relationsClient.createUserGroup(userGroup);
    }

    @HystrixCommand(fallbackMethod = "updateGroupFail")
    public void updateGroup(String uuidGroup, CreateGroupDto createGroupDto){
        groupClient.update(uuidGroup, createGroupDto);
    }

    @HystrixCommand(fallbackMethod = "deleteUserGroupGroupFail")
    public void deleteUserGroupGroup(String uuidGroup){
        relationsClient.deleteUserGroupGroup(uuidGroup);
    }

    @HystrixCommand(fallbackMethod = "deleteGroupFail")
    public void deleteGroup(String uuidGroup){
        groupClient.delete(uuidGroup);
    }

    public List<Group> findAllGroupsFail(){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }

    public Group findByIdGroupFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }

    public List<GroupReservation> findByGroupReservationIDUuidGroupFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public List<UserGroup> findByUserGroupIDUuidGroupFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public Reservation findByIdReservationFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public Group createGroupFail(Group group){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }

    public void createUserGroupFail(UserGroup userGroup){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public void updateGroupFail(String uuidGroup, CreateGroupDto createGroupDto){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }

    public void deleteUserGroupGroupFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public void deleteGroupFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }
}
