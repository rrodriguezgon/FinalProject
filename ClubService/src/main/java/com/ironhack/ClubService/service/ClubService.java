package com.ironhack.ClubService.service;

import com.ironhack.ClubService.exceptions.DataNotFoundException;
import com.ironhack.ClubService.model.Club;
import com.ironhack.ClubService.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    /**
     *
     * @return
     */
    public List<Club> findAll(){
        return clubRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Club findById(String id){
        return clubRepository.findById(id).orElseThrow(() -> new DataNotFoundException("This id Club not found."));
    }

    /**
     *
     * @param club
     * @return
     */
    public Club create(Club club) {
        return clubRepository.save(club);
    }

    /**
     *
     * @param id
     * @param club
     */
    public void update(String id, Club club){
        Club clubFound = findById(id);

        club.setId(clubFound.getId());

        clubRepository.save(club);
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        Club clubFound = findById(id);

        clubRepository.delete(clubFound);
    }
}
