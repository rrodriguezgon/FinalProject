package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.repository.GroupReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupReservationService {

    @Autowired
    private GroupReservationRepository groupReservationRepository;

    public List<GroupReservation> findAll(){
        return groupReservationRepository.findAll();
    }

    public List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup){
        return groupReservationRepository.findByGroupReservationIDUuidGroup(uuidGroup);
    }

    public List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation){
        return groupReservationRepository.findByGroupReservationIDUuidReservation(uuidReservation);
    }

    public GroupReservation create(GroupReservation groupReservation){
        return groupReservationRepository.save(groupReservation);
    }

    public void deleteReservation(String uuidReservation){
        List<GroupReservation> list = findByGroupReservationIDUuidReservation(uuidReservation);

        groupReservationRepository.deleteAll(list);
    }

    public void deleteGroup(String uuidGroup){
        List<GroupReservation> list = findByGroupReservationIDUuidGroup(uuidGroup);

        groupReservationRepository.deleteAll(list);
    }
}
