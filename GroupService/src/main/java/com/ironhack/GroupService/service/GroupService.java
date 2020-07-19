package com.ironhack.GroupService.service;

import com.ironhack.GroupService.dto.GroupUpdateDto;
import com.ironhack.GroupService.model.Group;
import com.ironhack.GroupService.repository.GroupRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private static final Logger LOGGER = LogManager.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    /**
     *
     * @return
     */
    public List<Group> findAll(){
        try {
            return groupRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Group findById(String id){
        try {
            Optional<Group> groupOptional = groupRepository.findById(id);

            if (groupOptional.isPresent()){
                return groupOptional.get();
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param group
     * @return
     */
    public Group create(Group group) {
        try {
            group.setCreatedAt(LocalDateTime.now());
            return groupRepository.save(group);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     * @param group
     */
    public void update(String id, GroupUpdateDto group){
        try {
            Optional<Group> groupOptional = groupRepository.findById(id);

            if (groupOptional.isPresent()){
                Group groupFound = groupOptional.get();

                groupFound.setName(group.getName());
                groupFound.setDescription(group.getDescription());
                groupFound.setImage(group.getImage());

                groupRepository.save(groupFound);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     *
     * @param id
     */
    public void delete(String id){
        try {
            Optional<Group> groupOptional = groupRepository.findById(id);

            if (groupOptional.isPresent()) {
                groupRepository.delete(groupOptional.get());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
