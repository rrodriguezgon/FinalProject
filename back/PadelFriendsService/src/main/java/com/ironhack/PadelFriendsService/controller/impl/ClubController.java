package com.ironhack.PadelFriendsService.controller.impl;

import com.ironhack.PadelFriendsService.controller.interfaces.IClubController;
import com.ironhack.PadelFriendsService.model.Entity.Club;
import com.ironhack.PadelFriendsService.model.ViewModel.ClubViewModel;
import com.ironhack.PadelFriendsService.service.ClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Club Controller")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClubController implements IClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/clubs")
    @ApiOperation(value="Get All Clubs",
            notes = "Display all Clubs",
            response = Club.class, responseContainer = "List")
    @ResponseStatus(HttpStatus.OK)
    public List<Club> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/clubs/{id}")
    @ApiOperation(value="Get Details Club",
            notes = "Display Details Club",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.OK)
    public ClubViewModel findById(@PathVariable("id") String id) {
        return clubService.findById(id);
    }

    @PostMapping("/clubs")
    @ApiOperation(value="Create new Club",
            notes = "Display Club Created",
            response = ClubViewModel.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ClubViewModel create(@RequestBody @Valid Club club) {
        return clubService.create(club);
    }

    @PutMapping("/clubs/{id}")
    @ApiOperation(value="Update Club",
            notes = "Display Club Updated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id,@RequestBody @Valid Club clubUpdated) {
        clubService.update(id,clubUpdated);
    }

    @DeleteMapping("/clubs/{id}")
    @ApiOperation(value="Delete Club",
            notes = "Delete Club")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        clubService.delete(id);
    }
}
