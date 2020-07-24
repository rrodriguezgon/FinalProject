package com.ironhack.ClubService.service;

import com.ironhack.ClubService.model.Club;
import com.ironhack.ClubService.repository.ClubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClubServiceTest {

    @Autowired
    private ClubService clubService;

    @MockBean
    private ClubRepository clubRepository;

    @BeforeEach
    void setUp() {
        Club club = new Club();
        club.setId("1");

        List<Club> clubList = Collections.singletonList(club);

        when(clubRepository.findAll()).thenReturn(clubList);
        when(clubRepository.findById(club.getId())).thenReturn(Optional.ofNullable(club));
        when(clubRepository.save(any(Club.class))).thenReturn(club);
        doAnswer(i -> {
            return null;
        }).when(clubRepository).delete(any(Club.class));
    }

    @Test
    void findAll() {
        int size = 1;

        assertEquals(size, clubService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(clubService.findById("1") instanceof Club);
    }

    @Test
    void findByIdNotFound() {
        assertFalse(clubService.findById("2") instanceof Club);
    }

    @Test
    void create() {
        assertTrue(clubService.create(new Club()) instanceof Club);
    }

    @Test
    void update() {
        clubService.update("1",new Club());

        assertEquals(1,1);
    }

    @Test
    void delete() {
        clubService.delete("1");

        assertEquals(1,1);
    }
}