package com.ironhack.ClubService.controller.impl;

import com.ironhack.ClubService.controller.interfaces.IClubController;
import com.ironhack.ClubService.model.Club;
import com.ironhack.ClubService.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClubController implements IClubController {

    @Autowired
    private ClubService clubService;

    /**
     *
     * @return
     */
    @GetMapping("/clubs")
    @ResponseStatus(HttpStatus.OK)
    public List<Club> findAll() {
        return clubService.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Club findById(@PathVariable("id") String id) {
        return clubService.findById(id);
    }

    /**
     *
     * @param club
     * @return
     */
    @PostMapping("/clubs")
    @ResponseStatus(HttpStatus.CREATED)
    public Club create(@RequestBody @Valid Club club) {
        return clubService.create(club);
    }

    /**
     *
     * @param id
     * @param club
     */
    @PutMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Club club) {
        clubService.update(id, club);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        clubService.delete(id);
    }
}
