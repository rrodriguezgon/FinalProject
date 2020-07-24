package com.ironhack.GroupService.service;

import com.ironhack.GroupService.dto.GroupUpdateDto;
import com.ironhack.GroupService.model.Group;
import com.ironhack.GroupService.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        Group group = new Group();
        group.setId("1");

        List<Group> clubList = Collections.singletonList(group);

        when(groupRepository.findAll()).thenReturn(clubList);
        when(groupRepository.findById(group.getId())).thenReturn(Optional.ofNullable(group));
        when(groupRepository.save(any(Group.class))).thenReturn(group);
        doAnswer(i -> {
            return null;
        }).when(groupRepository).delete(any(Group.class));
    }

    @Test
    void findAll() {
        int size = 1;

        assertEquals(size, groupService.findAll().size());
    }

    @Test
    void findById() {
        assertTrue(groupService.findById("1") instanceof Group);
    }

    @Test
    void findByIdNotFound() {
        assertFalse(groupService.findById("2") instanceof Group);
    }

    @Test
    void create() {
        assertTrue(groupService.create(new Group()) instanceof Group);
    }

    @Test
    void update() {
        groupService.update("1",new GroupUpdateDto());

        assertEquals(1,1);
    }

    @Test
    void delete() {
        groupService.delete("1");

        assertEquals(1,1);
    }
}