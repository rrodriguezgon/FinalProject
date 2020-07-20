package com.ironhack.PadelFriendsService.FallbackFunctions;

import com.ironhack.PadelFriendsService.client.ClubClient;
import com.ironhack.PadelFriendsService.client.ReservationClient;
import com.ironhack.PadelFriendsService.exceptions.ServiceNotAccessibleException;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClubServiceFallbackFunctions {
    @Autowired
    private ClubClient clubClient;


    @Autowired
    private ReservationClient reservationClient;

    @HystrixCommand(fallbackMethod = "findAllFail")
    public List<Club> findAll(){
        return clubClient.findAll();
    }

    @HystrixCommand(fallbackMethod = "findClubByClubIdFail")
    public Club findClubByClubId(String uuidClub){
        return clubClient.findById(uuidClub);
    }

    @HystrixCommand(fallbackMethod = "findReservationByClubIdFail")
    public List<Reservation> findReservationByClubId(String uuidClub){
        return reservationClient.findByClubId(uuidClub);
    }

    @HystrixCommand(fallbackMethod = "createFail")
    public Club create(Club club){
        return clubClient.create(club);
    }

    @HystrixCommand(fallbackMethod = "updateFail")
    public void update(String uuidClub, Club club){
        clubClient.update(uuidClub, club);
    }

    @HystrixCommand(fallbackMethod = "deleteFail")
    public void delete(String uuidClub){
        clubClient.delete(uuidClub);
    }

    public List<Club> findAllFail(){
        throw new ServiceNotAccessibleException("Club Service not Accessible, review logs.");
    }

    public Club findClubByClubIdFail(String uuidClub){
        throw new ServiceNotAccessibleException("Club Service not Accessible, review logs.");
    }

    public List<Reservation> findReservationByClubIdFail(String uuidClub){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }

    public Club createFail(Club club){
        throw new ServiceNotAccessibleException("Club Service not Accessible, review logs.");
    }

    public void updateFail(String uuidClub, Club club){
        throw new ServiceNotAccessibleException("Club Service not Accessible, review logs.");
    }

    public void deleteFail(String uuidClub){
        throw new ServiceNotAccessibleException("Club Service not Accessible, review logs.");
    }
}
