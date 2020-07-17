package com.ironhack.GroupService.controller.impl;

import com.ironhack.GroupService.controller.interfaces.GroupController;
import com.ironhack.GroupService.dto.GroupUpdateDto;
import com.ironhack.GroupService.model.Group;
import com.ironhack.GroupService.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupControllerImpl implements GroupController {
    @Autowired
    private GroupService groupService;

    /**
     *
     * @return
     */
    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> findAll() {
        return groupService.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Group findById(@PathVariable("id") String id) {
        return groupService.findById(id);
    }

    /**
     *
     * @param group
     * @return
     */
    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Group create(@RequestBody @Valid Group group) {
        return groupService.create(group);
    }

    /**
     *
     * @param id
     * @param groupUpdated
     */
    @PutMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid GroupUpdateDto groupUpdated) {
        groupService.update(id, groupUpdated);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        groupService.delete(id);
    }

}
