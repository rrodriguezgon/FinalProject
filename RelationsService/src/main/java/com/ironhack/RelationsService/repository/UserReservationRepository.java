package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.UserReservation;
import com.ironhack.RelationsService.model.UserReservationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReservationRepository extends JpaRepository<UserReservation, UserReservationID> {
}
