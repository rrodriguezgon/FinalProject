package com.ironhack.RelationsService.repository;

import com.ironhack.RelationsService.model.UserGroup;
import com.ironhack.RelationsService.model.UserGroupID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupID> {
}
