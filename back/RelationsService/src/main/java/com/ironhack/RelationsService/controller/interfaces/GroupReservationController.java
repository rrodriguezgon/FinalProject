package com.ironhack.RelationsService.controller.interfaces;

import com.ironhack.RelationsService.model.GroupReservation;

import java.util.List;

public interface GroupReservationController {

    public List<GroupReservation> findAll();

    public List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup);

    public List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation);

    public GroupReservation create(GroupReservation groupReservation);

    public void deleteReservation(String uuidReservation);

    public void deleteGroup(String uuidGroup);

}
