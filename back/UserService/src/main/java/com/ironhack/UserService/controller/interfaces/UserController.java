package com.ironhack.UserService.controller.interfaces;

import com.ironhack.UserService.model.User;

import java.util.List;
import java.util.Optional;

public interface UserController {

    /**
     *
     * @return
     */
    public List<User> findAll();

    /**
     *
     * @param id
     * @return
     */
    public User findById(String id);

    /**
     *
     * @param username
     * @return
     */
    public Optional<User> findByUsername(String username);

    /**
     *
     * @param user
     * @return
     */
    public User create(User user);

    /**
     *
     * @param id
     * @param user
     */
    public void update(String id, User user);

    /**
     *
     * @param id
     */
    public void delete(String id);
}
