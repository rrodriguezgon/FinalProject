package com.ironhack.PadelFriendsService.client;

import com.ironhack.PadelFriendsService.model.Entity.GroupReservation;
import com.ironhack.PadelFriendsService.model.Entity.UserGroup;
import com.ironhack.PadelFriendsService.model.Entity.UserReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "RELATIONS-SERVICE")
public interface RelationsClient {

    @GetMapping("/groupreservations")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findAllGroupReservation();

    @GetMapping("/groupreservations/group/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidGroup(String uuidGroup);

    @GetMapping("/groupreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupReservation> findByGroupReservationIDUuidReservation(String uuidReservation) ;

    @PostMapping("/groupreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupReservation createGroupReservation(GroupReservation groupReservation);

    @DeleteMapping("/groupreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupReservationReservation(String uuidReservation);

    @DeleteMapping("/groupreservations/group/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupReservationGroup(String uuidGroup);


    @GetMapping("/usergroups")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findAllUserGroup();

    @GetMapping("/usergroups/group/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup);

    @GetMapping("/usergroups/reservation/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGroup> findByUserGroupIDUuidUser(String uuidUser);

    @PostMapping("/usergroups")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroup createUserGroup(UserGroup userGroup);

    @DeleteMapping("/usergroups/reservation/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserGroupUser(String uuidUser);

    @DeleteMapping("/usergroups/group/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserGroupGroup(String uuidGroup);


    @GetMapping("/userreservations")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findAllUserReservation();

    @GetMapping("/userreservations/user/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidUser(String uuidUser);

    @GetMapping("/userreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReservation> findByUserReservationIDUuidReservation(String uuidReservation);

    @PostMapping("/userreservations")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReservation create(UserReservation userReservation);

    @DeleteMapping("/userreservations/user/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserReservationUser(String uuidUser);

    @DeleteMapping("/userreservations/reservation/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserReservationGroup(String uuidReservation);

}
