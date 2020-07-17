package com.ironhack.UserService.controller.impl;

import com.ironhack.UserService.controller.interfaces.UserController;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    /**
     *
     * @param id
     * @param user
     */
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid User user) {
        userService.update(id, user);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);
    }
}
