package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.model.UserGroupID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupID> {
    List<UserGroup> findByUserGroupIDUuidUser(String uuidUser);
    List<UserGroup> findByUserGroupIDUuidGroup(String uuidGroup);
}
