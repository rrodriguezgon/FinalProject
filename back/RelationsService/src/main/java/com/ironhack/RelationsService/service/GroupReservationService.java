package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.repository.GroupReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupReservationService {

    private static final Logger LOGGER = LogManager.getLogger(GroupReservationService.class);

    @Autowired
    private GroupReservationRepository groupReservationRepository;

    public List<GroupReservation> findAll(){
        try {
            return groupReservationRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup){
        try {
            return groupReservationRepository.findByGroupReservationIDUuidGroup(uuidGroup);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation){
        try {
            return groupReservationRepository.findByGroupReservationIDUuidReservation(uuidReservation);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public GroupReservation create(GroupReservation groupReservation){
        try {
            return groupReservationRepository.save(groupReservation);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public void deleteReservation(String uuidReservation){
        try {
            List<GroupReservation> list = findByGroupReservationIDUuidReservation(uuidReservation);

            groupReservationRepository.deleteAll(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public void deleteGroup(String uuidGroup){
        try {
            List<GroupReservation> list = findByGroupReservationIDUuidGroup(uuidGroup);

            groupReservationRepository.deleteAll(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
