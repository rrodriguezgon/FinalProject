package com.ironhack.RelationsService.controller.impl;

import com.ironhack.RelationsService.model.UserReservation;
import com.ironhack.RelationsService.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserReservationControllerImpl {

    @Autowired
    private UserReservationService userReservationService;

    @GetMapping("/userreservations")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findAll() {
        return userReservationService.findAll();
    }

    @GetMapping("/userreservations/user/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidUser(String uuidUser) {
        return userReservationService.findByUserReservationIDUuidUser(uuidUser);
    }

    @GetMapping("/userreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation) {
        return userReservationService.findByUserReservationIDUuidReservation(uuidReservation);
    }

    @PostMapping("/userreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReservation create(UserReservation userReservation) {
        return userReservationService.create(userReservation);
    }

    @DeleteMapping("/userreservations/user/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(String uuidUser) {
        userReservationService.deleteUser(uuidUser);
    }

    @DeleteMapping("/userreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(String uuidReservation) {
        userReservationService.deleteReservation(uuidReservation);
    }
}
