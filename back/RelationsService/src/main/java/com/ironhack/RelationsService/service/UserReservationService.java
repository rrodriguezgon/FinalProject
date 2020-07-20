package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.model.UserReservation;
import com.ironhack.RelationsService.repository.UserGroupRepository;
import com.ironhack.RelationsService.repository.UserReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReservationService {

    private static final Logger LOGGER = LogManager.getLogger(UserReservationService.class);

    @Autowired
    private UserReservationRepository userReservationRepository;

    public List<UserReservation> findAll(){
        try {
            return userReservationRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation){
        try {
            return userReservationRepository.findByUserReservationIDUuidReservation(uuidReservation);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public List<UserReservation> findByUserReservationIDUuidUser(String uuidUser){
        try {
            return userReservationRepository.findByUserReservationIDUuidUser(uuidUser);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public UserReservation create(UserReservation userReservation){
        try {
            return userReservationRepository.save(userReservation);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public void deleteUser(String uuidUser){
        try {
            List<UserReservation> list = findByUserReservationIDUuidUser(uuidUser);

            userReservationRepository.deleteAll(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public void deleteReservation(String uuidReservation){
        try {
            List<UserReservation> list = findByUserReservationIDUuidReservation(uuidReservation);

            userReservationRepository.deleteAll(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
