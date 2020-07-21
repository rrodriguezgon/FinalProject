package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IUserController;
import com.ironhack.PadelFriendsService.dto.LoginDto;
import com.ironhack.PadelFriendsService.model.Entity.Reservation;
import com.ironhack.PadelFriendsService.model.Entity.User;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import com.ironhack.PadelFriendsService.model.ViewModel.UserViewModel;
import com.ironhack.PadelFriendsService.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User Controller")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController implements IUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation(value="Get All Users",
            notes = "Display all Users",
            response = Reservation.class, responseContainer = "List")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value="Get Details User",
            notes = "Display Details User",
            response = UserViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public UserViewModel findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @PostMapping("/users/login")
    @ApiOperation(value="Login User",
            notes = "Login User",
            response = UserViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public UserViewModel login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/users")
    @ApiOperation(value="Create new User",
            notes = "Display User Created",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.CREATED)
    public UserViewModel create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @PutMapping("/users/{id}")
    @ApiOperation(value="Update User",
            notes = "Display User")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,@RequestBody @Valid User user) {
        userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(value="Delete User",
            notes = "Delete User")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
