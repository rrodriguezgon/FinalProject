package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.ReservationServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationListViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

    @Autowired
    private ReservationServiceFallbackFunctions serviceFallbackFunctions;

    public List<ReservationListViewModel> findAll(){
        List<Reservation> reservationList = serviceFallbackFunctions.findAll();

        List<ReservationListViewModel> reservationListViewModelList = new ArrayList<>();

        reservationList.forEach(reservation -> {
            Club club = serviceFallbackFunctions.findClubById(reservation.getClubId());
            String nameClub = "Club not Found";
            String provinceClub = "Club not Found";
            String cityClub = "Club not Found";
            if (club == null){
                DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. Parameter: " + reservation.getClubId());

                LOGGER.error(ex);
                throw ex;
            } else {
                nameClub = club.getName();
                provinceClub = club.getProvince();
                cityClub = club.getCity();
            }

            List<UserReservation> userReservationList =  serviceFallbackFunctions.findByUserReservationIDUuidReservation(reservation.getId());
            List<GroupReservation> groupReservationList = serviceFallbackFunctions.findByGroupReservationIDUuidReservation(reservation.getId());
            BigDecimal amountXPlayer;

            if (userReservationList.size() > 0){
                amountXPlayer = reservation.getAmount().divide(new BigDecimal(userReservationList.size()));
            } else {
                amountXPlayer = reservation.getAmount();
            }

            reservationListViewModelList.add(new ReservationListViewModel(reservation.getId(), nameClub, provinceClub, cityClub,
                    userReservationList.size(), reservation.getDate(), amountXPlayer, reservation.getPrivate() ));
        });

        return reservationListViewModelList;
    }

    public ReservationViewModel findById(String id){
        Reservation reservation = serviceFallbackFunctions.findReservationById(id);
        if (reservation == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Reservation not exists. Parameter: " + id);

            LOGGER.error(ex);
            throw ex;
        }

        Club club = serviceFallbackFunctions.findClubById(reservation.getClubId());

        if (club == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. Parameter: " + reservation.getClubId());

            LOGGER.error(ex);
            throw ex;
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
            DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. ClubId: " + createReservationDto.getClubId());

            LOGGER.error(ex);
            throw ex;
        }

        Reservation reservationCreated = serviceFallbackFunctions.createReservation(reservation);

        if ( createReservationDto.getUsers() != null && createReservationDto.getUsers().size() != 0){

            for (String userId : createReservationDto.getUsers()){
                User user = serviceFallbackFunctions.findUserById(userId);

                if (user == null){
                    DataNotFoundException ex = new DataNotFoundException("This user does not exist so it cannot be added to the reservation. UserId: " + userId);

                    LOGGER.error(ex);
                    throw ex;
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
                    DataNotFoundException ex = new DataNotFoundException("This group does not exist so it cannot be added to the reservation. GroupId: " + groupId);

                    LOGGER.error(ex);
                    throw ex;
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

        if (reservationFound == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Reservation not exists. Parameter: " + id);

            LOGGER.error(ex);
            throw ex;
        }

        Club club = serviceFallbackFunctions.findClubById(createReservationDto.getClubId());

        if (club == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. ClubId: " + createReservationDto.getClubId());

            LOGGER.error(ex);
            throw ex;
        }

        reservationFound.setClubId(createReservationDto.getClubId());
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
                    DataNotFoundException ex = new DataNotFoundException("This group does not exist so it cannot be added to the reservation. UserId: " + userId);

                    LOGGER.warn(ex);
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
                    DataNotFoundException ex = new DataNotFoundException("This group does not exist so it cannot be added to the reservation. GroupId:" + groupId);

                    LOGGER.warn(ex);
                }
            }
        }

        serviceFallbackFunctions.updateReservation(id,reservationFound);
    }

    public void delete(String id){
        Reservation reservationFound = serviceFallbackFunctions.findReservationById(id);

        if (reservationFound == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Reservation not exists. Parameter: " + id);

            LOGGER.error(ex);
            throw ex;
        }

        serviceFallbackFunctions.deleteReservation(id);

        serviceFallbackFunctions.deleteGroupReservationReservation(id);
        serviceFallbackFunctions.deleteUserReservationReservation(id);
    }
}
