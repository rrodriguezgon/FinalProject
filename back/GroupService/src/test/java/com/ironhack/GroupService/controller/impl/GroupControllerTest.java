package com.ironhack.GroupService.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.GroupService.dto.GroupUpdateDto;
import com.ironhack.GroupService.model.Group;
import com.ironhack.GroupService.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GroupControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private GroupService groupService;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        Group group = new Group();
        group.setId("1");

        List<Group> clubList = Collections.singletonList(group);

        when(groupService.findAll()).thenReturn(clubList);
        when(groupService.findById(group.getId())).thenReturn(group);
        when(groupService.create(any(Group.class))).thenReturn(group);
        doAnswer(i -> {
            return null;
        }).when(groupService).update(eq("1"),any(GroupUpdateDto.class));
        doAnswer(i -> {
            return null;
        }).when(groupService).delete("1");
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/groups/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/groups")
                .content(objectMapper.writeValueAsString(new Group()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/groups/1")
                .content(objectMapper.writeValueAsString(new Group()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteGroup() throws Exception {
        mockMvc.perform(delete("/groups/1"))
                .andExpect(status().isNoContent());
    }
}