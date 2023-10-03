package com.cinqeucento.engineservice.service.impl;

import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqeucento.engineservice.model.UsersProjectRoleEntity;
import com.cinqeucento.engineservice.repository.UsersProjectRoleRepository;
import com.cinqeucento.engineservice.service.UsersProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersProjectRoleServiceImpl implements UsersProjectRoleService {

    private final UsersProjectRoleRepository usersProjectRoleRepository;

    @Autowired
    public UsersProjectRoleServiceImpl(UsersProjectRoleRepository usersProjectRoleRepository) {
        this.usersProjectRoleRepository = usersProjectRoleRepository;
    }

    @Override
    public UsersProjectRoleEntity save(Long userId, Long projectId, UsersProjectRole role) {
        UsersProjectRoleEntity entity = new UsersProjectRoleEntity();

        entity.setUserId(userId);
        entity.setProjectId(projectId);
        entity.setUsersProjectRole(role);

        return usersProjectRoleRepository.save(entity);
    }

    @Override
    public List<UsersProjectRoleEntity> findUserProjectRoleByUserIdAndProjectId(Long userId, Long projectId) {
        return usersProjectRoleRepository.findUserProjectRoleByUserIdAndProjectId(userId, projectId);
    }
}
