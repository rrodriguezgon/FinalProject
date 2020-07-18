package com.ironhack.PadelFriendsService.client;

import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "RESERVATION-SERVICE")
public interface ReservationClient {

    /**
     *
     * @return
     */
    @GetMapping("/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findAll();

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation findById(@PathVariable("id") String id);

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/reservations/club/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findByClubId(@PathVariable("id") String id);

    /**
     *
     * @param reservation
     * @return
     */
    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody @Valid Reservation reservation) ;

    /**
     *
     * @param id
     * @param reservationUpdated
     */
    @PutMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Reservation reservationUpdated);

    /**
     *
     * @param id
     */
    @DeleteMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id);

    /**
     *
     * @return
     */
    @GetMapping("/reservations/status")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getStatusList();
}
