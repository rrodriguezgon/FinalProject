package com.ironhack.PadelFriendsService.controller.interfaces;

import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;

import java.util.List;

public interface IReservationController {

    public List<Reservation> findAll();
    public ReservationViewModel findById(String id);
    public ReservationViewModel create(CreateReservationDto createReservationDto);
    public void update(String id, CreateReservationDto createReservationDto);
    public void delete(String id);
}
