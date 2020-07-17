package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.model.GroupReservationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupReservationRepository extends JpaRepository<GroupReservation, GroupReservationID> {
}
