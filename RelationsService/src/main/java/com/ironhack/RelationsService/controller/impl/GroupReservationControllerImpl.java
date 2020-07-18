package com.ironhack.RelationsService.controller.impl;

import com.ironhack.RelationsService.controller.interfaces.GroupReservationController;
import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.service.GroupReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupReservationControllerImpl implements GroupReservationController {

    @Autowired
    private GroupReservationService groupReservationService;

    @GetMapping("/groupreservations")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findAll() {
        return groupReservationService.findAll();
    }

    @GetMapping("/groupreservations/group/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup) {
        return groupReservationService.findByGroupReservationIDUuidGroup(uuidGroup);
    }

    @GetMapping("/groupreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation) {
        return groupReservationService.findByGroupReservationIDUuidReservation(uuidReservation);
    }

    @PostMapping("/groupreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupReservation create(GroupReservation groupReservation) {
        return groupReservationService.create(groupReservation);
    }

    @DeleteMapping("/groupreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(String uuidReservation) {
        groupReservationService.deleteReservation(uuidReservation);
    }

    @DeleteMapping("/groupreservations/group/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(String uuidGroup) {
        groupReservationService.deleteGroup(uuidGroup);
    }
}
