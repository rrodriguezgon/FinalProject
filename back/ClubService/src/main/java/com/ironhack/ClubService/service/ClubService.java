package com.ironhack.ClubService.service;

import com.ironhack.ClubService.model.Club;
import com.ironhack.ClubService.repository.ClubRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private static final Logger LOGGER = LogManager.getLogger(ClubService.class);

    @Autowired
    private ClubRepository clubRepository;

    /**
     *
     * @return
     */
    public List<Club> findAll(){
        try {
            return clubRepository.findAll();
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
    public Club findById(String id){
        try {
            Optional<Club> clubOptional = clubRepository.findById(id);

            if (clubOptional.isPresent()){
                return clubOptional.get();
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
     * @param club
     * @return
     */
    public Club create(Club club) {
        try {
            return clubRepository.save(club);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @param club
     */
    public void update(String id, Club club){
        try {
            Optional<Club> clubOptional = clubRepository.findById(id);

            if (clubOptional.isPresent()){
                club.setId(clubOptional.get().getId());

                clubRepository.save(club);
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
            Optional<Club> clubOptional = clubRepository.findById(id);

            if (clubOptional.isPresent()) {
                clubRepository.delete(clubOptional.get());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
