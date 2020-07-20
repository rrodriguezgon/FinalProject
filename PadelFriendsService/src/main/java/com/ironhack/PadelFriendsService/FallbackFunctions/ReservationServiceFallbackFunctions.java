package com.ironhack.PadelFriendsService.FallbackFunctions;

import com.ironhack.PadelFriendsService.client.*;
import com.ironhack.PadelFriendsService.exceptions.ServiceNotAccessibleException;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationServiceFallbackFunctions {

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

    @HystrixCommand(fallbackMethod = "findAllFail")
    public List<Reservation> findAll(){
        return reservationClient.findAll();
    }

    @HystrixCommand(fallbackMethod = "findReservationByIdFail")
    public Reservation findReservationById(String uuidReservation){
        return reservationClient.findById(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "findClubByIdFail")
    public Club findClubById(String uuidClub){
        return clubClient.findById(uuidClub);
    }

    @HystrixCommand(fallbackMethod = "updateReservationFail")
    public void updateReservation(String uuidReservation, Reservation reservationUpdated){
        reservationClient.update(uuidReservation, reservationUpdated);
    }

    @HystrixCommand(fallbackMethod = "findByUserReservationIDUuidReservationFail")
    public List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation){
        return relationsClient.findByUserReservationIDUuidReservation(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "findByGroupReservationIDUuidReservationFail")
    public List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation){
        return relationsClient.findByGroupReservationIDUuidReservation(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "findUserByIdFail")
    public User findUserById(String uuidUser){
        return userClient.findById(uuidUser);
    }

    @HystrixCommand(fallbackMethod = "findGroupByIdFail")
    public Group findGroupById(String uuidGroup){
        return groupClient.findById(uuidGroup);
    }

    @HystrixCommand(fallbackMethod = "createReservationFail")
    public Reservation createReservation(Reservation reservation){
        return reservationClient.create(reservation);
    }

    @HystrixCommand(fallbackMethod = "createUserReservationFail")
    public UserReservation createUserReservation(UserReservation userReservation){
        return relationsClient.create(userReservation);
    }

    @HystrixCommand(fallbackMethod = "createGroupReservationFail")
    public GroupReservation createGroupReservation(GroupReservation groupReservation){
        return relationsClient.createGroupReservation(groupReservation);
    }

    @HystrixCommand(fallbackMethod = "deleteGroupReservationReservationFail")
    public void deleteGroupReservationReservation(String uuidReservation){
        relationsClient.deleteGroupReservationReservation(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "deleteUserReservationReservationFail")
    public void deleteUserReservationReservation(String uuidReservation){
        relationsClient.deleteUserReservationReservation(uuidReservation);
    }

    @HystrixCommand(fallbackMethod = "deleteReservationFail")
    public void deleteReservation(String uuidReservation){
        reservationClient.delete(uuidReservation);
    }

    public List<Reservation> findAllFail(){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }

    public Reservation findReservationByIdFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }

    public void updateReservationFail(String uuidReservation, Reservation reservationUpdated){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }

    public Club findClubByIdFail(String uuidClub){
        throw new ServiceNotAccessibleException("Club Service not Accessible, review logs.");
    }

    public List<UserReservation> findByUserReservationIDUuidReservationFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public List<GroupReservation> findByGroupReservationIDUuidReservationFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public User findUserByIdFail(String uuidUser){
        throw new ServiceNotAccessibleException("User Service not Accessible, review logs.");
    }

    public Group findGroupByIdFail(String uuidGroup){
        throw new ServiceNotAccessibleException("Group Service not Accessible, review logs.");
    }

    public Reservation createReservationFail(Reservation reservation){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }

    public UserReservation createUserReservationFail(UserReservation userReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public GroupReservation createGroupReservationFail(GroupReservation groupReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public void deleteGroupReservationReservationFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public void deleteUserReservationReservationFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Relation Service not Accessible, review logs.");
    }

    public void deleteReservationFail(String uuidReservation){
        throw new ServiceNotAccessibleException("Reservation Service not Accessible, review logs.");
    }
}
