package com.ironhack.RelationsService.controller.interfaces;

import com.ironhack.RelationsService.model.UserReservation;

import java.util.List;

public interface UserReservationController {

    public List<UserReservation> findAll();

    public List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation);

    public List<UserReservation> findByUserReservationIDUuidUser(String uuidUser);

    public UserReservation create(UserReservation userReservation);

    public void deleteUser(String uuidUser);

    public void deleteReservation(String uuidReservation);

}
