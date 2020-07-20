package com.ironhack.ReservationService.controller.impl;

import com.ironhack.ReservationService.controller.interfaces.IReservationController;
import com.ironhack.ReservationService.model.Reservation;
import com.ironhack.ReservationService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@RestController
public class ReservationController implements IReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     *
     * @return
     */
    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation findById(@PathVariable("id") String id) {
        return reservationService.findById(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/reservations/club/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findByClubId(@PathVariable("id") String id) {
        return reservationService.findByClubId(id);
    }

    /**
     *
     * @param reservation
     * @return
     */
    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody @Valid Reservation reservation) {
        return reservationService.create(reservation);
    }

    /**
     *
     * @param id
     * @param reservationUpdated
     */
    @PutMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Reservation reservationUpdated) {
        reservationService.update(id, reservationUpdated);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        reservationService.delete(id);
    }

    /**
     *
     * @return
     */
    @GetMapping("/reservations/status")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getStatusList() {
        return reservationService.getStatusList();
    }
}
