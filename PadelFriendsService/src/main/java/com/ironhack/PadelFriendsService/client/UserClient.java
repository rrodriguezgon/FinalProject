package com.ironhack.PadelFriendsService.client;

import com.ironhack.PadelFriendsService.model.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {
    /**
     *
     * @return
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll();

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable("id") String id);

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid User user);

    /**
     *
     * @param id
     * @param user
     */
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid User user);

    /**
     *
     * @param id
     */
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id);
}
