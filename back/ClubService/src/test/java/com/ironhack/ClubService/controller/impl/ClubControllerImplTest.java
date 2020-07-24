package com.ironhack.ClubService.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.ClubService.model.Club;
import com.ironhack.ClubService.service.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClubControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ClubController clubController;

    @MockBean
    private ClubService clubService;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        Club club = new Club();
        club.setId("1");

        List<Club> clubList = Collections.singletonList(club);

        when(clubService.findAll()).thenReturn(clubList);
        when(clubService.findById(club.getId())).thenReturn(club);
        when(clubService.create(any(Club.class))).thenReturn(club);
        doAnswer(i -> {
            return null;
        }).when(clubService).update(eq("1"),any(Club.class));
        doAnswer(i -> {
            return null;
        }).when(clubService).delete("1");
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/clubs"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/clubs/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/clubs")
                .content(objectMapper.writeValueAsString(new Club()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/clubs/1")
                .content(objectMapper.writeValueAsString(new Club()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteClub() throws Exception {
        mockMvc.perform(delete("/clubs/1"))
                .andExpect(status().isNoContent());
    }
}