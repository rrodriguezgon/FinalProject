package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IReservationController;
import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;
import com.ironhack.PadelFriendsService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservationController implements IReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationViewModel findById(@PathVariable("id") String id) {
        return reservationService.findById(id);
    }

    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public ReservationViewModel create(@RequestBody @Valid CreateReservationDto createReservationDto) {
        return reservationService.create(createReservationDto);
    }

    @PutMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String id,@RequestBody @Valid CreateReservationDto createReservationDto) {
        reservationService.update(id, createReservationDto);
    }

    @DeleteMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        reservationService.delete(id);
    }
}
