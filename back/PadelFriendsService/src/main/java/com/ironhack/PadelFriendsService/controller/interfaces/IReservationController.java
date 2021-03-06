package com.ironhack.PadelFriendsService.controller.interfaces;

import com.ironhack.PadelFriendsService.dto.CreateReservationDto;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.Entity.User;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationListViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.ReservationViewModel;

import java.util.List;

public interface IReservationController {

    public List<ReservationListViewModel> findAll();
    public ReservationViewModel findById(String id);
    public ReservationViewModel create(CreateReservationDto createReservationDto);
    public void update(User user, String id, CreateReservationDto createReservationDto);
    public void delete(String id);
}
