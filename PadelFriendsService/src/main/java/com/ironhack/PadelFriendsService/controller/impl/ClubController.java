package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IClubController;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import com.ironhack.PadelFriendsService.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClubController implements IClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/clubs")
    @ResponseStatus(HttpStatus.OK)
    public List<Club> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClubViewModel findById(@PathVariable("id") String id) {
        return clubService.findById(id);
    }

    @PostMapping("/clubs")
    @ResponseStatus(HttpStatus.CREATED)
    public ClubViewModel create(@RequestBody @Valid Club club) {
        return clubService.create(club);
    }

    @PutMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,@RequestBody @Valid Club clubUpdated) {
        clubService.update(id,clubUpdated);
    }

    @DeleteMapping("/clubs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        clubService.delete(id);
    }
}
