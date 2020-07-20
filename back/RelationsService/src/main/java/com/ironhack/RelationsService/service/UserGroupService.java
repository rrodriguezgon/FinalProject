package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.repository.UserGroupRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {

    private static final Logger LOGGER = LogManager.getLogger(UserGroupService.class);

    @Autowired
    private UserGroupRepository userGroupRepository;

    public List<UserGroup> findAll(){
        try {
            return userGroupRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup){
        try {
            return userGroupRepository.findByUserGroupIDUuidGroup(uuidGroup);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public List<UserGroup> findByUserGroupIDUuidUser(String uuidUser){
        try {
            return userGroupRepository.findByUserGroupIDUuidUser(uuidUser);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public UserGroup create(UserGroup userGroup){
        try {
            return userGroupRepository.save(userGroup);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public void deleteUser(String uuidUser){
        try {
            List<UserGroup> list = findByUserGroupIDUuidUser(uuidUser);

            userGroupRepository.deleteAll(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }

    public void deleteGroup(String uuidGroup){
        try {
            List<UserGroup> list = findByUserGroupIDUuidGroup(uuidGroup);

            userGroupRepository.deleteAll(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
