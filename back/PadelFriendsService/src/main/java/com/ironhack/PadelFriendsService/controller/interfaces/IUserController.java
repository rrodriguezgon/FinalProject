package com.ironhack.PadelFriendsService.controller.interfaces;

import com.ironhack.PadelFriendsService.model.Entity.User;
import com.ironhack.PadelFriendsService.model.ViewModel.UserViewModel;

import java.util.List;

public interface IUserController {

    public List<User> findAll();

    public UserViewModel findById(String id);

    public UserViewModel create(User user);

    public void update(String id, User user);

    public void delete(String id);
}
