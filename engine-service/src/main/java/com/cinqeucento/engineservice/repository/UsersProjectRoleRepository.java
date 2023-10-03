package com.cinqeucento.engineservice.repository;

import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqeucento.engineservice.model.UsersProjectRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersProjectRoleRepository extends JpaRepository<UsersProjectRoleEntity, Long> {

    List<UsersProjectRoleEntity> findUserProjectRoleByUserIdAndProjectId(Long userId, Long projectId);

}
