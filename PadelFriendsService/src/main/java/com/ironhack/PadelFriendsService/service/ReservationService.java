package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.client.*;
import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationClient reservationClient;

    @Autowired
    private ClubClient clubClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private GroupClient groupClient;

    @Autowired
    private RelationsClient relationsClient;

    public List<Reservation> findAll(){
        return reservationClient.findAll();
    }

    public ReservationViewModel findById(String id){
        Reservation reservation = reservationClient.findById(id);
        Club club = clubClient.findById(reservation.getClubId());
        List<User> userList = new ArrayList<>();
        List<Group> groupList = new ArrayList<>();

        List<UserReservation> userReservationList =  relationsClient.findByUserReservationIDUuidReservation(id);
        List<GroupReservation> groupReservationList = relationsClient.findByGroupReservationIDUuidReservation(id);

        for (UserReservation userReservation : userReservationList){
            User user = userClient.findById(userReservation.getUserReservationID().getUuidUser());

            userList.add(user);
        }

        for (GroupReservation groupReservation : groupReservationList){
            Group group = groupClient.findById(groupReservation.getGroupReservationID().getUuidGroup());

            groupList.add(group);
        }

        return new ReservationViewModel(reservation.getId(), club, reservation.getAmount(),
                reservation.getDate(), reservation.getPrivate(),reservation.getStatus(), userList, groupList);
    }

    public ReservationViewModel create(CreateReservationDto createReservationDto){
        Reservation reservation = new Reservation(createReservationDto.getClubId(), createReservationDto.getAmount(),
                createReservationDto.getDate(), createReservationDto.getPrivate(),createReservationDto.getStatus());
        List<User> userList = new ArrayList<>();
        List<Group> groupList = new ArrayList<>();

        Reservation reservationCreated = reservationClient.create(reservation);
        Club club = clubClient.findById(createReservationDto.getClubId());

        if ( createReservationDto.getUsers() != null && createReservationDto.getUsers().size() != 0){

            for (String userId : createReservationDto.getUsers()){
                User user = userClient.findById(userId);

                userList.add(user);

                UserReservation userReservation = new UserReservation(new UserReservationID(reservationCreated.getId(),userId));

                relationsClient.create(userReservation);
            }
        }

        if ( createReservationDto.getGroups() != null && createReservationDto.getGroups().size() != 0){

            for (String groupId : createReservationDto.getGroups()){
                Group group = groupClient.findById(groupId);

                groupList.add(group);

                GroupReservation groupReservation = new GroupReservation(new GroupReservationID(reservationCreated.getId(),groupId));

                relationsClient.createGroupReservation(groupReservation);
            }
        }

        return new ReservationViewModel(reservation.getId(), club, reservation.getAmount(),
                reservation.getDate(), reservation.getPrivate(),reservation.getStatus(), userList, groupList);
    }

    public void update(String id, CreateReservationDto createReservationDto){
        Reservation reservationFound = reservationClient.findById(id);
        reservationFound.setAmount(createReservationDto.getAmount());
        reservationFound.setDate(createReservationDto.getDate());
        reservationFound.setPrivate(createReservationDto.getPrivate());
        reservationFound.setStatus(createReservationDto.getStatus());

        relationsClient.deleteGroupReservationReservation(id);
        relationsClient.deleteUserReservationReservation(id);

        if ( createReservationDto.getUsers() != null && createReservationDto.getUsers().size() != 0){

            for (String userId : createReservationDto.getUsers()){
                User user = userClient.findById(userId);

                UserReservation userReservation = new UserReservation(new UserReservationID(id,userId));

                relationsClient.create(userReservation);
            }
        }

        if ( createReservationDto.getGroups() != null && createReservationDto.getGroups().size() != 0){

            for (String groupId : createReservationDto.getGroups()){
                Group group = groupClient.findById(groupId);

                GroupReservation groupReservation = new GroupReservation(new GroupReservationID(id,groupId));

                relationsClient.createGroupReservation(groupReservation);
            }
        }
    }

    public void delete(String id){
        reservationClient.delete(id);

        relationsClient.deleteGroupReservationReservation(id);
        relationsClient.deleteUserReservationReservation(id);
    }
}
