package com.ironhack.UserService.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private Role role;

    @Test
    void values() {
        role = Role.ROLE_ADMIN;
        role = Role.ROLE_PLAYER;
    }
}