package com.ironhack.RelationsService.service;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    public List<UserGroup> findAll(){
        return userGroupRepository.findAll();
    }

    public List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup){
        return userGroupRepository.findByUserGroupIDUuidGroup(uuidGroup);
    }

    public List<UserGroup> findByUserGroupIDUuidUser(String uuidUser){
        return userGroupRepository.findByUserGroupIDUuidUser(uuidUser);
    }

    public UserGroup create(UserGroup userGroup){
        return userGroupRepository.save(userGroup);
    }

    public void deleteUser(String uuidUser){
        List<UserGroup> list = findByUserGroupIDUuidUser(uuidUser);

        userGroupRepository.deleteAll(list);
    }

    public void deleteGroup(String uuidGroup){
        List<UserGroup> list = findByUserGroupIDUuidGroup(uuidGroup);

        userGroupRepository.deleteAll(list);
    }
}
