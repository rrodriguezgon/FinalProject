package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.UserReservation;
import com.ironhack.RelationsService.model.UserReservationID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReservationRepository extends CrudRepository<UserReservation, UserReservationID> {
    List<UserReservation> findByUserReservationIDUuidUser(String uuidUser);
    List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation);
}
