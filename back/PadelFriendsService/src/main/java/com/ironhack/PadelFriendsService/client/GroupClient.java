package com.ironhack.PadelFriendsService.client;

import com.ironhack.PadelFriendsService.dto.CreateGroupDto;
import com.ironhack.PadelFriendsService.model.Entity.Group;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="GROUP-SERVICE", url="https://groupservice.herokuapp.com")
public interface GroupClient {

    /**
     *
     * @return
     */
    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> findAll();

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Group findById(@PathVariable("id") String id);

    /**
     *
     * @param group
     * @return
     */
    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Group create(@RequestBody @Valid CreateGroupDto group);

    /**
     *
     * @param id
     * @param groupUpdated
     */
    @PutMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody @Valid CreateGroupDto groupUpdated);

    /**
     *
     * @param id
     */
    @DeleteMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id);
}
