package com.ironhack.PadelFriendsService.FallbackFunctions;

import com.ironhack.PadelFriendsService.client.GroupClient;
import com.ironhack.PadelFriendsService.client.RelationsClient;
import com.ironhack.PadelFriendsService.client.ReservationClient;
import com.ironhack.PadelFriendsService.client.UserClient;
import com.ironhack.PadelFriendsService.exceptions.ServiceNotAccessibleException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceFallbackFunctions {

    @Autowired
    private UserClient userClient;

    @Autowired
    private GroupClient groupClient;

    @Autowired
    private ReservationClient reservationClient;

    @Autowired
    private RelationsClient relationsClient;

    @HystrixCommand(fallbackMethod = "findAllFail")
    public List<User> findAll(){
        return userClient.findAll();
    }

    @HystrixCommand(fallbackMethod = "findUserByIdFail")
    public User findUserById(String uuidUser){
        return userClient.findById(uuidUser);
    }

    @HystrixCommand(fallbackMethod = "findUserByUsernameFail")
    public Optional<User> findByUsername(String username){
        return userClient.findByUsername(username);
    }

    @HystrixCommand(fallbackMethod = "findByUserGroupIDUuidUserFail")
    public List<UserGroup> findByUserGroupIDUuidUser(String uuidUser){
        return relationsClient.findByUserGroupIDUuidUser(uuidUser);
    }

    @HystrixCommand(fallbackMethod = "findByUserReservationIDUuidUserFail")
    public List<UserReservation> findByUserReservationIDUuidUser(String uuidUser){
        return relationsClient.findByUserReservationIDUuidUser(uuidUser);
    }

    @HystrixCommand(fallbackMethod = "findGroupByIdFail")
    public Group findGroupById(String uuidGroup){
        return groupClient.findById(uuidGroup);
    }

    @HystrixCommand(fallbackMethod = "findReservationByIdFail")
    public Reservation findReservationById(String uuidReservation){
        return reservationClient.findById(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "createUserFail")
    public User createUser(User user){
        return userClient.create(user);
    }

    @HystrixCommand(fallbackMethod = "updateFail")
    public void updateUser(String uuidUser, User user){
        userClient.update(uuidUser, user);
    }

    @HystrixCommand(fallbackMethod = "deleteUserFail")
    public void deleteUser(String uuidUser){
        userClient.delete(uuidUser);
    }

    @HystrixCommand(fallbackMethod = "deleteUserGroupUserFail")
    public void deleteUserGroupUser(String uuidUser){
        relationsClient.deleteUserGroupUser(uuidUser);
    }

    @HystrixCommand(fallbackMethod = "deleteUserReservationUserFail")
    public void deleteUserReservationUser(String uuidUser){
        relationsClient.deleteUserReservationUser(uuidUser);
    }

    public List<User> findAllFail(){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public User findUserByIdFail(String uuidUser){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public Optional<User> findUserByUsernameFail(String username){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public List<UserGroup> findByUserGroupIDUuidUserFail(String uuidUser){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public List<UserReservation> findByUserReservationIDUuidUserFail(String uuidUser){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public Group findGroupByIdFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }

    public Reservation findReservationByIdFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }

    public User createUserFail(User user){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public void updateFail(String uuidUser, User user){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public void deleteUserFail(String uuidUser){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public void deleteUserGroupUserFail(String uuidUser){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public void deleteUserReservationUserFail(String uuidUser){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }
}
