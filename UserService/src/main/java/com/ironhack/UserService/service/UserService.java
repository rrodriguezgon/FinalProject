package com.ironhack.UserService.service;

import com.ironhack.UserService.exceptions.DataNotFoundException;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("This id Club not found."));
    }

    /**
     *
     * @param user
     * @return
     */
    public User create(User user) {
        return userRepository.save(user);
    }

    /**
     *
     * @param id
     * @param user
     */
    public void update(String id, User user){
        User userFound = findById(id);

        user.setId(userFound.getId());

        userRepository.save(user);
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        User userFound = findById(id);

        userRepository.delete(userFound);
    }
}
