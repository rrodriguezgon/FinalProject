package com.ironhack.GroupService.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    private Group group;

    @BeforeEach
    void setUp() {
        group = new Group();
        group = new Group("","","","","");
    }

    @Test
    void getId() {
        String param = "1";
        group.setId(param);

        assertEquals(param, group.getId());
    }

    @Test
    void getName() {
        String param = "1";
        group.setName(param);

        assertEquals(param, group.getName());
    }

    @Test
    void getDescription() {
        String param = "1";
        group.setDescription(param);

        assertEquals(param, group.getDescription());
    }

    @Test
    void getProvince() {
        String param = "1";
        group.setProvince(param);

        assertEquals(param, group.getProvince());
    }

    @Test
    void getCity() {
        String param = "1";
        group.setCity(param);

        assertEquals(param, group.getCity());
    }

    @Test
    void getImage() {
        String param = "1";
        group.setImage(param);

        assertEquals(param, group.getImage());
    }

    @Test
    void getCreatedAt() {
        LocalDateTime param = LocalDateTime.now();
        group.setCreatedAt(param);

        assertEquals(param, group.getCreatedAt());
    }
}