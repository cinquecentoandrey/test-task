package com.cinqeucento.engineservice.util;

import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqeucento.engineservice.model.UsersProjectRoleEntity;
import com.cinqeucento.engineservice.service.impl.UsersProjectRoleServiceImpl;
import com.cinquecento.jwtstarter.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersProjectRoleAccessChecker {

    private final JWTUtil jwtUtil;
    private final UsersProjectRoleServiceImpl usersProjectRoleService;

    @Autowired
    public UsersProjectRoleAccessChecker(JWTUtil jwtUtil,
                                         UsersProjectRoleServiceImpl usersProjectRoleService) {
        this.jwtUtil = jwtUtil;
        this.usersProjectRoleService = usersProjectRoleService;
    }

    public boolean checkAccess(String token, Long projectId, List<UsersProjectRole> roles) {
        Long userId = 1L;
        //Long userId = Long.valueOf(jwtUtil.validateTokenAndRetrieveClaims(token.substring(7)).get("id"));
        List<UsersProjectRoleEntity> list = usersProjectRoleService.findUserProjectRoleByUserIdAndProjectId(userId, projectId);

        return list.stream().anyMatch(entity -> roles.contains(entity.getUsersProjectRole()));
    }

}
