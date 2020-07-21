package com.ironhack.PadelFriendsService.client;

import com.ironhack.PadelFriendsService.model.Entity.GroupReservation;
import com.ironhack.PadelFriendsService.model.Entity.UserGroup;
import com.ironhack.PadelFriendsService.model.Entity.UserReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "RELATIONS-SERVICE", url="https://relationservice.herokuapp.com")
public interface RelationsClient {

    @GetMapping("/groupreservations")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findAllGroupReservation();

    @GetMapping("/groupreservations/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidGroup(@PathVariable("id") String uuidGroup);

    @GetMapping("/groupreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidReservation(@PathVariable("id") String uuidReservation) ;

    @PostMapping("/groupreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupReservation createGroupReservation(@RequestBody @Valid GroupReservation groupReservation);

    @DeleteMapping("/groupreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupReservationReservation(@PathVariable("id") String uuidReservation);

    @DeleteMapping("/groupreservations/group/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupReservationGroup(@PathVariable("id") String uuidGroup);


    @GetMapping("/usergroups")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findAllUserGroup();

    @GetMapping("/usergroups/group/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidGroup(@PathVariable("id") String uuidGroup);

    @GetMapping("/usergroups/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidUser(@PathVariable("id") String uuidUser);

    @PostMapping("/usergroups")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroup createUserGroup(@RequestBody @Valid UserGroup userGroup);

    @DeleteMapping("/usergroups/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserGroupUser(@PathVariable("id") String uuidUser);

    @DeleteMapping("/usergroups/group/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserGroupGroup(@PathVariable("id") String uuidGroup);


    @GetMapping("/userreservations")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findAllUserReservation();

    @GetMapping("/userreservations/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidUser(@PathVariable("id") String uuidUser);

    @GetMapping("/userreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidReservation(@PathVariable("id") String uuidReservation);

    @PostMapping("/userreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReservation create(@RequestBody @Valid UserReservation userReservation);

    @DeleteMapping("/userreservations/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserReservationUser(@PathVariable("id") String uuidUser);

    @DeleteMapping("/userreservations/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserReservationReservation(@PathVariable("id") String uuidReservation);

}
