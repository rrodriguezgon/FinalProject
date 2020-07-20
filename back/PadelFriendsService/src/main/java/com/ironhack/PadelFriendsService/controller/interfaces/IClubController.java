package com.ironhack.PadelFriendsService.controller.interfaces;

import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;

import java.util.List;

public interface IClubController {

    public List<Club> findAll();

    public ClubViewModel findById(String uuidClub);

    public ClubViewModel create(Club club);

    public void update(String uuidClub, Club clubUpdated);

    public void delete(String uuidClub);
}
