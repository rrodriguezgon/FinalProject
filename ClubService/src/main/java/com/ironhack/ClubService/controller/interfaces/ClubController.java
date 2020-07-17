package com.ironhack.ClubService.controller.interfaces;

import com.ironhack.ClubService.model.Club;

import java.util.List;

public interface ClubController {

    /**
     *
     * @return
     */
    public List<Club> findAll();

    /**
     *
     * @param id
     * @return
     */
    public Club findById(String id);

    /**
     *
     * @param group
     * @return
     */
    public Club create(Club group);

    /**
     *
     * @param id
     * @param groupUpdated
     */
    public void update(String id, Club groupUpdated);

    /**
     *
     * @param id
     */
    public void delete(String id);
}
