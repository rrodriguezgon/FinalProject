package com.ironhack.GroupService.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupUpdateDtoTest {

    private GroupUpdateDto groupUpdateDto;

    @BeforeEach
    void setUp() {
        groupUpdateDto = new GroupUpdateDto();
        groupUpdateDto = new GroupUpdateDto("","","","","");
    }

    @Test
    void getName() {
        String param = "1";
        groupUpdateDto.setName(param);

        assertEquals(param, groupUpdateDto.getName());
    }

    @Test
    void getDescription() {
        String param = "1";
        groupUpdateDto.setDescription(param);

        assertEquals(param, groupUpdateDto.getDescription());
    }

    @Test
    void getProvince() {
        String param = "1";
        groupUpdateDto.setProvince(param);

        assertEquals(param, groupUpdateDto.getProvince());
    }

    @Test
    void getCity() {
        String param = "1";
        groupUpdateDto.setCity(param);

        assertEquals(param, groupUpdateDto.getCity());
    }

    @Test
    void getImage() {
        String param = "1";
        groupUpdateDto.setImage(param);

        assertEquals(param, groupUpdateDto.getImage());
    }
}