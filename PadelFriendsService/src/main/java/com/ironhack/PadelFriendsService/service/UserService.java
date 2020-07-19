package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.UserServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.UserViewModel;
import com.ironhack.PadelFriendsService.security.CustomSecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserServiceFallbackFunctions serviceFallbackFunctions;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> findAll(){
        return serviceFallbackFunctions.findAll();
    }

    public UserViewModel findById(String id){
        User user = serviceFallbackFunctions.findUserById(id);

        if (user == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid user not exists. UserId: " + id);
            LOGGER.error(ex);
            throw ex;
        }

        List<Group> groupList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        List<UserGroup> userGroupList = serviceFallbackFunctions.findByUserGroupIDUuidUser(id);
        List<UserReservation> userReservationList = serviceFallbackFunctions.findByUserReservationIDUuidUser(id);

        for (UserGroup userGroup : userGroupList) {
            Group group = serviceFallbackFunctions.findGroupById(userGroup.getUserGroupID().getUuidGroup());

            if (group != null){
                groupList.add(group);
            } else {
                DataNotFoundException ex = new DataNotFoundException("This group does not exist so it cannot be added to the reservation. GroupId:" + userGroup.getUserGroupID().getUuidGroup());

                LOGGER.warn(ex);
            }
        }

        for (UserReservation userReservation : userReservationList) {
            Reservation reservation = serviceFallbackFunctions.findReservationById(userReservation.getUserReservationID().getUuidReservation());

            if (reservation != null){
                reservationList.add(reservation);
            } else {
                DataNotFoundException ex = new DataNotFoundException("This group does not exist so it cannot be added to the reservation. GroupId:" + userReservation.getUserReservationID().getUuidReservation());

                LOGGER.warn(ex);
            }
        }

        return new UserViewModel(user.getId(), user.getUsername(), user.getName(), user.getRole(), groupList, reservationList);
    }

    public UserViewModel create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userCreated = serviceFallbackFunctions.createUser(user);
        List<Group> groupList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();

        return new UserViewModel(userCreated.getId(), userCreated.getUsername(), userCreated.getName(), userCreated.getRole(), groupList, reservationList);
    }

    public void update(String id, User user){
        User userFound = serviceFallbackFunctions.findUserById(id);

        if (userFound == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid user not exists. UserId: " + id);
            LOGGER.error(ex);
            throw ex;
        }

        serviceFallbackFunctions.updateUser(id, user);
    }

    public void delete(String id){
        User userFound = serviceFallbackFunctions.findUserById(id);

        if (userFound == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid user not exists. UserId: " + id);
            LOGGER.error(ex);
            throw ex;
        }

        serviceFallbackFunctions.deleteUser(id);

        serviceFallbackFunctions.deleteUserGroupUser(id);
        serviceFallbackFunctions.deleteUserReservationUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Load user by username " + username);
        Optional<User> user = serviceFallbackFunctions.findByUsername(username);
        System.out.println("Usuario buscado");
        return new CustomSecurityUser(user.orElseThrow(() ->
                new UsernameNotFoundException("Invalid username/password combination.")));
    }
}
