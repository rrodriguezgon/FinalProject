package com.ironhack.ClubService.model;

import com.google.inject.internal.cglib.core.$ClassInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClubTest {

    private Club club;

    @BeforeEach
    void setUp() {
        club = new Club();
        club = new Club("ubication","name","city","province",1,new BigDecimal("1"), new BigDecimal("1"));
    }

    @Test
    void getId() {
        String param = "1";
        club.setId(param);

        assertEquals(param, club.getId());
    }

    @Test
    void getUbication() {
        String param = "1";
        club.setUbication(param);

        assertEquals(param, club.getUbication());
    }

    @Test
    void getName() {
        String param = "1";
        club.setName(param);

        assertEquals(param, club.getName());
    }

    @Test
    void getNumberCourts() {
        int param = 1;
        club.setNumberCourts(param);

        assertEquals(param, club.getNumberCourts());
    }

    @Test
    void getCity() {
        String param = "1";
        club.setCity(param);

        assertEquals(param, club.getCity());
    }

    @Test
    void getProvince() {
        String param = "1";
        club.setProvince(param);

        assertEquals(param, club.getProvince());
    }

    @Test
    void getLatitude() {
        BigDecimal param = new BigDecimal("1");
        club.setLatitude(param);

        assertEquals(param, club.getLatitude());
    }

    @Test
    void getLongitude() {
        BigDecimal param = new BigDecimal("1");
        club.setLongitude(param);

        assertEquals(param, club.getLongitude());
    }
}