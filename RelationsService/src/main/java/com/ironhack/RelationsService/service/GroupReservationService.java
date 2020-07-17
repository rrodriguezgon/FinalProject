package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.repository.GroupReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupReservationService {

    @Autowired
    private GroupReservationRepository groupReservationRepository;

}
