package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.model.GroupReservationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupReservationRepository extends JpaRepository<GroupReservation, GroupReservationID> {
    List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup);
    List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation);
}
