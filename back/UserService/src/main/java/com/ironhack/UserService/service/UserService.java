package com.ironhack.UserService.service;

import com.ironhack.UserService.exceptions.DataNotFoundException;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return
     */
    public List<User> findAll(){
        try {
            return userRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public User findById(String id){
        try {
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()){
                return userOptional.get();
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param username
     * @return
     */
    public Optional<User> findByUsername(String username){
        try {
            Optional<User> userOptional = userRepository.findByUsername(username);

            return userOptional;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param user
     * @return
     */
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @param user
     */
    public void update(String id, User user){
        try {
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()) {
                User userFound = userOptional.get();

                user.setId(userFound.getId());
                userRepository.save(user);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        try {
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()) {
                User userFound = userOptional.get();

                userRepository.delete(userFound);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
