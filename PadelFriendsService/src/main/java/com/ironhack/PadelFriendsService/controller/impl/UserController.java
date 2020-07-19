package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IUserController;
import com.ironhack.PadelFriendsService.model.Entity.User;
import com.ironhack.PadelFriendsService.model.ViewModel.UserViewModel;
import com.ironhack.PadelFriendsService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController implements IUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserViewModel findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserViewModel create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,@RequestBody @Valid User user) {
        userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
