package com.ironhack.ReservationService.service;

import com.ironhack.ReservationService.enums.StatusReservation;
import com.ironhack.ReservationService.exceptions.DataNotFoundException;
import com.ironhack.ReservationService.model.Reservation;
import com.ironhack.ReservationService.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     *
     * @return
     */
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Reservation findById(String id) {
        return reservationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("This id Reservation not found."));
    }

    /**
     *
     * @param reservation
     * @return
     */
    public Reservation create(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    /**
     *
     * @param id
     * @param reservation
     */
    public void update(String id, Reservation reservation){
        Reservation reservationFound = findById(id);

        reservation.setId(reservationFound.getId());

        reservationRepository.save(reservation);
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        Reservation reservationFound = findById(id);

        reservationRepository.delete(reservationFound);
    }

    /**
     *
     * @return
     */
    public List<String> getStatusList(){
        return Arrays.stream(StatusReservation.values()).map(x -> x.toString()).collect(Collectors.toList());
    }
}
