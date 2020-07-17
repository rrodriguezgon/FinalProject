package com.ironhack.GroupService.service;

import com.ironhack.GroupService.dto.GroupUpdateDto;
import com.ironhack.GroupService.exceptions.DataNotFoundException;
import com.ironhack.GroupService.model.Group;
import com.ironhack.GroupService.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    /**
     *
     * @return
     */
    public List<Group> findAll(){
        return groupRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Group findById(String id){
        return groupRepository.findById(id).orElseThrow(() -> new DataNotFoundException("This id Group not found."));
    }

    /**
     *
     * @param group
     * @return
     */
    public Group create(Group group) {
        group.setCreatedAt(LocalDateTime.now());
        return groupRepository.save(group);
    }

    /**
     *
     * @param id
     * @param group
     */
    public void update(String id, GroupUpdateDto group){
        Group groupFound = findById(id);

        groupFound.setName(group.getName());
        groupFound.setDescription(group.getDescription());
        groupFound.setImage(group.getImage());

        groupRepository.save(groupFound);
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        Group groupFound = findById(id);

        groupRepository.delete(groupFound);
    }
}
