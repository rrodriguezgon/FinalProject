package com.ironhack.PadelFriendsService.client;

import com.ironhack.PadelFriendsService.model.Entity.Club;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "CLUB-SERVICE", url = "https://clubservice.herokuapp.com")
public interface ClubClient {

    /**
     *
     * @return
     */
    @GetMapping("/clubs")
    @ResponseStatus(HttpStatus.OK)
    public List<Club> findAll();

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Club findById(@PathVariable("id") String id);

    /**
     *
     * @param club
     * @return
     */
    @PostMapping("/clubs")
    @ResponseStatus(HttpStatus.CREATED)
    public Club create(@RequestBody @Valid Club club);

    /**
     *
     * @param id
     * @param club
     */
    @PutMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Club club);

    /**
     *
     * @param id
     */
    @DeleteMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id);
}
