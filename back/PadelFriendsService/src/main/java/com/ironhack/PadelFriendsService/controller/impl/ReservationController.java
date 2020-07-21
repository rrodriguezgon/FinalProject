package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IReservationController;
import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.Entity.User;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;
import com.ironhack.PadelFriendsService.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Reservation Controller")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController implements IReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    @ApiOperation(value="Get All Reservations",
            notes = "Display all Reservations",
            response = Reservation.class, responseContainer = "List")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/reservations/{id}")
    @ApiOperation(value="Get Details Reservation",
            notes = "Display Details Reservation",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public ReservationViewModel findById(@PathVariable("id") String id) {
        return reservationService.findById(id);
    }

    @PostMapping("/reservations")
    @ApiOperation(value="Create new Reservation",
            notes = "Display Reservation Created",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public ReservationViewModel create(@RequestBody @Valid CreateReservationDto createReservationDto) {
        return reservationService.create(createReservationDto);
    }

    @PutMapping("/reservations/{id}")
    @ApiOperation(value="Update Reservation",
            notes = "Display Reservation")
    @ResponseStatus(HttpStatus.OK)
    public void update(@AuthenticationPrincipal User user, @PathVariable("id") String id, @RequestBody @Valid CreateReservationDto createReservationDto) {
        reservationService.update(user, id, createReservationDto);
    }

    @DeleteMapping("/reservations/{id}")
    @ApiOperation(value="Delete Reservation",
            notes = "Delete Reservation")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        reservationService.delete(id);
    }
}
