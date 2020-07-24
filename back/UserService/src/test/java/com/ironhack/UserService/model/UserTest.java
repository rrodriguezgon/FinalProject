package com.ironhack.UserService.model;

import com.ironhack.UserService.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user = new User("","","", Role.ROLE_ADMIN);
    }

    @Test
    void getId() {
        String param = "1";
        user.setId(param);

        assertEquals(param, user.getId());
    }

    @Test
    void getUsername() {
        String param = "1";
        user.setUsername(param);

        assertEquals(param, user.getUsername());
    }

    @Test
    void getName() {
        String param = "1";
        user.setName(param);

        assertEquals(param, user.getName());
    }

    @Test
    void getPassword() {
        String param = "1";
        user.setPassword(param);

        assertEquals(param, user.getPassword());
    }

    @Test
    void getRole() {
        String param = "1";
        user.setName(param);

        assertEquals(param, user.getName());
    }
}