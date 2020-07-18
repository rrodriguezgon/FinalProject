package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.model.UserReservation;
import com.ironhack.RelationsService.repository.UserGroupRepository;
import com.ironhack.RelationsService.repository.UserReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReservationService {

    @Autowired
    private UserReservationRepository userReservationRepository;

    public List<UserReservation> findAll(){
        return userReservationRepository.findAll();
    }

    public List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation){
        return userReservationRepository.findByUserReservationIDUuidReservation(uuidReservation);
    }

    public List<UserReservation> findByUserReservationIDUuidUser(String uuidUser){
        return userReservationRepository.findByUserReservationIDUuidUser(uuidUser);
    }

    public UserReservation create(UserReservation userReservation){
        return userReservationRepository.save(userReservation);
    }

    public void deleteUser(String uuidUser){
        List<UserReservation> list = findByUserReservationIDUuidUser(uuidUser);

        userReservationRepository.deleteAll(list);
    }

    public void deleteReservation(String uuidReservation){
        List<UserReservation> list = findByUserReservationIDUuidReservation(uuidReservation);

        userReservationRepository.deleteAll(list);
    }
}
