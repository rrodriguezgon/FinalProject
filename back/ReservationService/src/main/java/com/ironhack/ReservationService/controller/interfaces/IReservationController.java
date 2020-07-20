package com.ironhack.ReservationService.controller.interfaces;

import com.ironhack.ReservationService.model.Reservation;

import java.util.List;

/**
 *
 */
public interface IReservationController {

    /**
     *
     * @return
     */
    public List<Reservation> findAll();

    /**
     *
     * @param id
     * @return
     */
    public Reservation findById(String id);

    /**
     *
     * @param id
     * @return
     */
    public List<Reservation> findByClubId(String id);

    /**
     *
     * @param reservation
     * @return
     */
    public Reservation create(Reservation reservation);

    /**
     *
     * @param id
     * @param reservationUpdated
     */
    public void update(String id, Reservation reservationUpdated);

    /**
     *
     * @param id
     */
    public void delete(String id);

    /**
     *
     * @return
     */
    public List<String> getStatusList();
}
