package com.ironhack.RelationsService.controller.impl;

import com.ironhack.RelationsService.controller.interfaces.GroupReservationController;
import com.ironhack.RelationsService.model.GroupReservation;
import com.ironhack.RelationsService.service.GroupReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/groupreservations/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidGroup(@PathVariable("id") String id) {
        return groupReservationService.findByGroupReservationIDUuidGroup(id);
    }

    @GetMapping("/groupreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidReservation(@PathVariable("id") String id) {
        return groupReservationService.findByGroupReservationIDUuidReservation(id);
    }

    @PostMapping("/groupreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupReservation create(@RequestBody @Valid GroupReservation groupReservation) {
        return groupReservationService.create(groupReservation);
    }

    @DeleteMapping("/groupreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable("id") String uuidReservation) {
        groupReservationService.deleteReservation(uuidReservation);
    }

    @DeleteMapping("/groupreservations/group/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable("id") String uuidGroup) {
        groupReservationService.deleteGroup(uuidGroup);
    }
}
