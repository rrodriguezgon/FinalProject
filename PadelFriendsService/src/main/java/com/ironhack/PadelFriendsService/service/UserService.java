package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.UserServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserServiceFallbackFunctions serviceFallbackFunctions;

    public List<User> findAll(){
        return serviceFallbackFunctions.findAll();
    }

    public UserViewModel findById(String id){
        User user = serviceFallbackFunctions.findUserById(id);

        if (user == null){
            throw new DataNotFoundException("This uuid user not exists.");
        }

        List<Group> groupList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        List<UserGroup> userGroupList = serviceFallbackFunctions.findByUserGroupIDUuidUser(id);
        List<UserReservation> userReservationList = serviceFallbackFunctions.findByUserReservationIDUuidUser(id);

        for (UserGroup userGroup : userGroupList) {
            Group group = serviceFallbackFunctions.findGroupById(userGroup.getUserGroupID().getUuidGroup());

            if (group != null){
                groupList.add(group);
            }
        }

        for (UserReservation userReservation : userReservationList) {
            Reservation reservation = serviceFallbackFunctions.findReservationById(userReservation.getUserReservationID().getUuidReservation());

            if (reservation != null){
                reservationList.add(reservation);
            }
        }

        return new UserViewModel(user.getId(), user.getUsername(), user.getName(), user.getRole(), groupList, reservationList);
    }

    public UserViewModel create(User user){
        User userCreated = serviceFallbackFunctions.createUser(user);
        List<Group> groupList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        return new UserViewModel(userCreated.getId(), userCreated.getUsername(), userCreated.getName(), userCreated.getRole(), groupList, reservationList);
    }

    public void update(String id, User user){
        serviceFallbackFunctions.updateUser(id, user);
    }

    public void delete(String id){
        serviceFallbackFunctions.deleteUser(id);

        serviceFallbackFunctions.deleteUserGroupUser(id);
        serviceFallbackFunctions.deleteUserReservationUser(id);
    }
}
