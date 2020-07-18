package com.ironhack.RelationsService.controller.impl;

import com.ironhack.RelationsService.model.UserReservation;
import com.ironhack.RelationsService.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/userreservations/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidUser(@PathVariable("id") String uuidUser) {
        return userReservationService.findByUserReservationIDUuidUser(uuidUser);
    }

    @GetMapping("/userreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidReservation(@PathVariable("id") String uuidReservation) {
        return userReservationService.findByUserReservationIDUuidReservation(uuidReservation);
    }

    @PostMapping("/userreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReservation create(@RequestBody @Valid UserReservation userReservation) {
        return userReservationService.create(userReservation);
    }

    @DeleteMapping("/userreservations/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String uuidUser) {
        userReservationService.deleteUser(uuidUser);
    }

    @DeleteMapping("/userreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable("id") String uuidReservation) {
        userReservationService.deleteReservation(uuidReservation);
    }
}
