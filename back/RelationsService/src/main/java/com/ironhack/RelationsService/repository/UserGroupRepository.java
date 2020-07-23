package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.model.UserGroupID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, UserGroupID> {
    List<UserGroup> findByUserGroupIDUuidUser(String uuidUser);
    List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup);
}
