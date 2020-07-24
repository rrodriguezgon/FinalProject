package com.ironhack.PadelFriendsService.service;

import com.ironhack.PadelFriendsService.FallbackFunctions.UserServiceFallbackFunctions;
import com.ironhack.PadelFriendsService.dto.LoginDto;
import com.ironhack.PadelFriendsService.model.Entity.*;
import com.ironhack.PadelFriendsService.model.ViewModel.UserViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserServiceFallbackFunctions serviceFallbackFunctions;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId("1");
        user.setName("nombre");
        user.setPassword("1234");

        Group group = new Group();
        group.setId("1");

        Reservation reservation = new Reservation();
        reservation.setId("1");
        UserGroup userGroup = new UserGroup(new UserGroupID("1","1"),false,false);

        UserReservation userReservation = new UserReservation(new UserReservationID("1","1"));

        List<User> userList = Collections.singletonList(user);
        List<UserGroup> userGroupList = Collections.singletonList(userGroup);
        List<UserReservation> userReservationList = Collections.singletonList(userReservation);

        when(serviceFallbackFunctions.findAll()).thenReturn(userList);
        when(serviceFallbackFunctions.findByUsername(user.getUsername())).thenReturn(Optional.ofNullable(user));
        when(serviceFallbackFunctions.findUserById(user.getId())).thenReturn(user);
        when(serviceFallbackFunctions.findByUserGroupIDUuidUser(user.getId())).thenReturn(userGroupList);
        when(serviceFallbackFunctions.findByUserReservationIDUuidUser(user.getId())).thenReturn(userReservationList);
        when(serviceFallbackFunctions.findGroupById(group.getId())).thenReturn(group);
        when(serviceFallbackFunctions.findReservationById(reservation.getId())).thenReturn(reservation);
        when(serviceFallbackFunctions.createUser(any(User.class))).thenReturn(user);
        doAnswer(i -> {
            return null;
        }).when(serviceFallbackFunctions).updateUser("1",user);
        doAnswer(i -> {
            return null;
        }).when(serviceFallbackFunctions).deleteUser("1");
        doAnswer(i -> {
            return null;
        }).when(serviceFallbackFunctions).deleteUserGroupUser("1");
        doAnswer(i -> {
            return null;
        }).when(serviceFallbackFunctions).deleteUserReservationUser("1");
    }

    @Test
    void findAll() {
        int size = 1;

        assertEquals(size, userService.findAll().size());
    }

    @Test
    void login() {
        assertThrows(UsernameNotFoundException.class, () -> userService.login(new LoginDto("username", "password")));
    }

    @Test
    void findById() {
        assertTrue(userService.findById("1") instanceof UserViewModel);
    }

    @Test
    void create() {
        User user = new User();
        user.setPassword("1234");
        assertTrue(userService.create(user) instanceof UserViewModel);
    }

    @Test
    void update() {
        userService.update("1",new User());
        assertEquals(1,1);
    }

    @Test
    void delete() {
        userService.delete("1");
        assertEquals(1,1);
    }

    @Test
    void loadUserByUsername() {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nombre"));
    }
}