package com.cinqeucento.engineservice.service;

import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqeucento.engineservice.model.UsersProjectRoleEntity;

import java.util.List;

public interface UsersProjectRoleService {

    UsersProjectRoleEntity save(Long userId, Long projectId, UsersProjectRole role);

    List<UsersProjectRoleEntity> findUserProjectRoleByUserIdAndProjectId(Long userId, Long projectId);


}
