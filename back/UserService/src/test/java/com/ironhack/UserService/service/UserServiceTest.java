package com.ironhack.UserService.service;

import com.ironhack.UserService.model.User;
import com.ironhack.UserService.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId("1");

        List<User> clubList = Collections.singletonList(user);

        when(userRepository.findAll()).thenReturn(clubList);
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        doAnswer(i -> {
            return null;
        }).when(userRepository).delete(any(User.class));
    }

    @Test
    void findAll() {
        int size = 1;

        assertEquals(size, userService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(userService.findById("1") instanceof User);
    }

    @Test
    void findByIdNotFound() {
        assertFalse(userService.findById("2") instanceof User);
    }

    @Test
    void findByUsername() {
        Optional<User> user = Optional.empty();

        assertEquals(user, userService.findByUsername("2"));
    }

    @Test
    void create() {
        assertTrue(userService.create(new User()) instanceof User);
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
}