package com.ironhack.ReservationService.service;

import com.ironhack.ReservationService.enums.StatusReservation;
import com.ironhack.ReservationService.model.Reservation;
import com.ironhack.ReservationService.repository.ReservationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ReservationService {

    private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     *
     * @return
     */
    public List<Reservation> findAll(){
        try {
            return reservationRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Reservation findById(String id) {
        try {
            Optional<Reservation> reservationOptional = reservationRepository.findById(id);

            if ( reservationOptional.isPresent()){
                return reservationOptional.get();
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public List<Reservation> findByClubId(String id) {
        try {
            return reservationRepository.findByClubId(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param reservation
     * @return
     */
    public Reservation create(Reservation reservation){
        try {
            return reservationRepository.save(reservation);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @param reservationUpdated
     */
    public void update(String id, Reservation reservationUpdated){
        try {
            Optional<Reservation> reservationOptional = reservationRepository.findById(id);

            if (reservationOptional.isPresent()) {
                Reservation reservation = reservationOptional.get();

                reservationUpdated.setId(reservation.getId());

                reservationRepository.save(reservationUpdated);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        try {
            Optional<Reservation> reservationOptional = reservationRepository.findById(id);

            if (reservationOptional.isPresent()) {

                reservationRepository.delete(reservationOptional.get());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @return
     */
    public List<String> getStatusList(){
        return Arrays.stream(StatusReservation.values()).map(x -> x.toString()).collect(Collectors.toList());
    }
}
