package com.ironhack.GroupService.controller.interfaces;

import com.ironhack.GroupService.dto.GroupUpdateDto;
import com.ironhack.GroupService.model.Group;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface IGroupController {
    /**
     *
     * @return
     */
    public List<Group> findAll();

    /**
     *
     * @param id
     * @return
     */
    public Group findById(String id);

    /**
     *
     * @param group
     * @return
     */
    public Group create(Group group);

    /**
     *
     * @param id
     * @param groupUpdated
     */
    public void update(String id, GroupUpdateDto groupUpdated);

    /**
     *
     * @param id
     */
    public void delete(String id);
}
