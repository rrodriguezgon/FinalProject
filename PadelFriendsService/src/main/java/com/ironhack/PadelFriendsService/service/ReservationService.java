package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.ReservationServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationServiceFallbackFunctions serviceFallbackFunctions;

    public List<Reservation> findAll(){
        return serviceFallbackFunctions.findAll();
    }

    public ReservationViewModel findById(String id){
        Reservation reservation = serviceFallbackFunctions.findReservationById(id);
        if (reservation == null){
            throw new DataNotFoundException("This uuid Reservation not exists.");
        }

        Club club = serviceFallbackFunctions.findClubById(reservation.getClubId());

        if (club == null){
            throw new DataNotFoundException("This uuid Club not exists.");
        }

        List<User> userList = new ArrayList<>();
        List<Group> groupList = new ArrayList<>();

        List<UserReservation> userReservationList =  serviceFallbackFunctions.findByUserReservationIDUuidReservation(id);
        List<GroupReservation> groupReservationList = serviceFallbackFunctions.findByGroupReservationIDUuidReservation(id);

        for (UserReservation userReservation : userReservationList){
            User user = serviceFallbackFunctions.findUserById(userReservation.getUserReservationID().getUuidUser());

            if (user != null){
                userList.add(user);
            }
        }

        for (GroupReservation groupReservation : groupReservationList){
            Group group = serviceFallbackFunctions.findGroupById(groupReservation.getGroupReservationID().getUuidGroup());

            if (group != null){
                groupList.add(group);
            }
        }

        return new ReservationViewModel(reservation.getId(), club, reservation.getAmount(),
                reservation.getDate(), reservation.getPrivate(),reservation.getStatus(), userList, groupList);
    }

    public ReservationViewModel create(CreateReservationDto createReservationDto){
        Reservation reservation = new Reservation(createReservationDto.getClubId(), createReservationDto.getAmount(),
                createReservationDto.getDate(), createReservationDto.getPrivate(),createReservationDto.getStatus());
        List<User> userList = new ArrayList<>();
        List<Group> groupList = new ArrayList<>();
        Club club = serviceFallbackFunctions.findClubById(createReservationDto.getClubId());

        if (club == null){
            throw new DataNotFoundException("This uuid Club not exists.");
        }

        Reservation reservationCreated = serviceFallbackFunctions.createReservation(reservation);

        if ( createReservationDto.getUsers() != null && createReservationDto.getUsers().size() != 0){

            for (String userId : createReservationDto.getUsers()){
                User user = serviceFallbackFunctions.findUserById(userId);

                if (user == null){
                    throw new DataNotFoundException("This user does not exist so it cannot be added to the reservation.");
                }

                userList.add(user);

                UserReservation userReservation = new UserReservation(new UserReservationID(reservationCreated.getId(),userId));

                serviceFallbackFunctions.createUserReservation(userReservation);
            }
        }

        if ( createReservationDto.getGroups() != null && createReservationDto.getGroups().size() != 0){

            for (String groupId : createReservationDto.getGroups()){
                Group group = serviceFallbackFunctions.findGroupById(groupId);

                if (group == null){
                    throw new DataNotFoundException("This group does not exist so it cannot be added to the reservation.");
                }

                groupList.add(group);

                GroupReservation groupReservation = new GroupReservation(new GroupReservationID(groupId,reservationCreated.getId()));

                serviceFallbackFunctions.createGroupReservation(groupReservation);
            }
        }

        return new ReservationViewModel(reservationCreated.getId(), club, reservation.getAmount(),
                reservation.getDate(), reservation.getPrivate(),reservation.getStatus(), userList, groupList);
    }

    public void update(User userlogin ,String id, CreateReservationDto createReservationDto){
        Reservation reservationFound = serviceFallbackFunctions.findReservationById(id);
        reservationFound.setAmount(createReservationDto.getAmount());
        reservationFound.setDate(createReservationDto.getDate());
        reservationFound.setPrivate(createReservationDto.getPrivate());
        reservationFound.setStatus(createReservationDto.getStatus());

        serviceFallbackFunctions.deleteGroupReservationReservation(id);
        serviceFallbackFunctions.deleteUserReservationReservation(id);

        if ( createReservationDto.getUsers() != null && createReservationDto.getUsers().size() != 0){

            for (String userId : createReservationDto.getUsers()){
                User user = serviceFallbackFunctions.findUserById(userId);

                if (user != null){

                    UserReservation userReservation = new UserReservation(new UserReservationID(id,userId));

                    serviceFallbackFunctions.createUserReservation(userReservation);
                } else {
                    // LOG throw new DataNotFoundException("This user does not exist so it cannot be added to the reservation.");
                }
            }
        }

        if ( createReservationDto.getGroups() != null && createReservationDto.getGroups().size() != 0){

            for (String groupId : createReservationDto.getGroups()){
                Group group = serviceFallbackFunctions.findGroupById(groupId);

                if (group != null){

                    GroupReservation groupReservation = new GroupReservation(new GroupReservationID(groupId,id));

                    serviceFallbackFunctions.createGroupReservation(groupReservation);
                } else {
                   // LOG throw new DataNotFoundException("This group does not exist so it cannot be added to the reservation.");
                }
            }
        }
    }

    public void delete(String id){
        serviceFallbackFunctions.deleteReservation(id);

        serviceFallbackFunctions.deleteGroupReservationReservation(id);
        serviceFallbackFunctions.deleteUserReservationReservation(id);
    }
}
