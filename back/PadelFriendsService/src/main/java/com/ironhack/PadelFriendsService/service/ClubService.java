package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.ClubServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.exceptions.DataNotFoundException;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubService {

    private static final Logger LOGGER = LogManager.getLogger(ClubService.class);

    @Autowired
    private ClubServiceFallbackFunctions fallbackFunctions;

    public List<Club> findAll(){
        return fallbackFunctions.findAll();
    }

    public ClubViewModel findById(String uuidClub){
        Club club = fallbackFunctions.findClubByClubId(uuidClub);

        if (club == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. ClubId: " + uuidClub);

            LOGGER.error(ex);
            throw ex;
        }

        List<Reservation> reservationList = fallbackFunctions.findReservationByClubId(uuidClub);

        return new ClubViewModel(club.getId(),club.getUbication(),
                club.getName(),club.getNumberCourts(),reservationList);
    }

    public ClubViewModel create(Club club){
        Club clubCreated = fallbackFunctions.create(club);
        List<Reservation> reservationList = new ArrayList<>();

        return new ClubViewModel(clubCreated.getId(),clubCreated.getUbication(),
                clubCreated.getName(),clubCreated.getNumberCourts(),reservationList);
    }

    public void update(String uuidClub, Club clubUpdated){
        Club club = fallbackFunctions.findClubByClubId(uuidClub);

        if (club == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. ClubId: " + uuidClub);

            LOGGER.error(ex);
            throw ex;
        }

        fallbackFunctions.update(uuidClub,clubUpdated);
    }

    public void delete(String uuidClub){
        Club club = fallbackFunctions.findClubByClubId(uuidClub);

        if (club == null){
            DataNotFoundException ex = new DataNotFoundException("This uuid Club not exists. ClubId: " + uuidClub);

            LOGGER.error(ex);
            throw ex;
        }

        fallbackFunctions.delete(uuidClub);
    }
}
