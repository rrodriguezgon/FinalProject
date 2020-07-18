package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.client.ClubClient;
import com.ironhack.PadelFriendsService.client.ReservationClient;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubService {
    @Autowired
    private ClubClient clubClient;

    @Autowired
    private ReservationClient reservationClient;

    public List<Club> findAll(){
        return clubClient.findAll();
    }

    public ClubViewModel findById(String uuidClub){
        Club club = clubClient.findById(uuidClub);
        List<Reservation> reservationList = reservationClient.findByClubId(uuidClub);

        return new ClubViewModel(club.getId(),club.getUbication(),
                club.getName(),club.getNumberCourts(),reservationList);
    }

    public ClubViewModel create(Club club){
        Club clubCreated = clubClient.create(club);
        List<Reservation> reservationList = new ArrayList<>();

        return new ClubViewModel(clubCreated.getId(),clubCreated.getUbication(),
                clubCreated.getName(),clubCreated.getNumberCourts(),reservationList);
    }

    public void update(String uuidClub, Club clubUpdated){
        clubClient.update(uuidClub,clubUpdated);
    }

    public void delete(String uuidClub){

        clubClient.delete(uuidClub);
    }
}
