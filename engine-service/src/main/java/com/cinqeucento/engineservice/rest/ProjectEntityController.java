package com.cinqeucento.engineservice.rest;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cinqeucento.engineservice.mapper.ProjectEntityMapper;
import com.cinqeucento.engineservice.mapper.TaskEntityMapper;
import com.cinqeucento.engineservice.model.ProjectEntity;
import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqeucento.engineservice.service.impl.ProjectEntityServiceImpl;
import com.cinqeucento.engineservice.service.impl.TaskEntityServiceImpl;
import com.cinqeucento.engineservice.service.impl.UsersProjectRoleServiceImpl;
import com.cinqeucento.engineservice.util.ErrorMessageBuilder;
import com.cinqeucento.engineservice.util.UsersProjectRoleAccessChecker;
import com.cinqucento.engineservicestarter.dto.ProjectEntityDTO;
import com.cinqucento.engineservicestarter.dto.TaskEntityDTO;
import com.cinqucento.engineservicestarter.exception.*;
import com.cinquecento.jwtstarter.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectEntityController {

    @Value("${entity-error-code.not-created}")
    private Integer NOT_CREATED;

    @Value("${entity-error-code.not-updated}")
    private Integer NOT_UPDATED;

    private final ProjectEntityServiceImpl projectEntityService;
    private final TaskEntityServiceImpl taskEntityService;
    private final ProjectEntityMapper projectEntityMapper;
    private final ErrorMessageBuilder errorMessageBuilder;
    private final TaskEntityMapper taskEntityMapper;
    private final JWTUtil jwtUtil;
    private final UsersProjectRoleServiceImpl usersProjectRoleService;
    private final UsersProjectRoleAccessChecker usersProjectRoleAccessChecker;

    @Autowired
    public ProjectEntityController(ProjectEntityServiceImpl projectEntityService,
                                   TaskEntityServiceImpl taskEntityService,
                                   ProjectEntityMapper projectEntityMapper,
                                   ErrorMessageBuilder errorMessageBuilder,
                                   TaskEntityMapper taskEntityMapper, JWTUtil jwtUtil,
                                   UsersProjectRoleServiceImpl usersProjectRoleService,
                                   UsersProjectRoleAccessChecker usersProjectRoleAccessChecker) {
        this.projectEntityService = projectEntityService;
        this.taskEntityService = taskEntityService;
        this.projectEntityMapper = projectEntityMapper;
        this.errorMessageBuilder = errorMessageBuilder;
        this.taskEntityMapper = taskEntityMapper;
        this.jwtUtil = jwtUtil;
        this.usersProjectRoleService = usersProjectRoleService;
        this.usersProjectRoleAccessChecker = usersProjectRoleAccessChecker;
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectEntityDTO> create(@RequestHeader("Authorization") String token,
                                                   @RequestBody @Valid ProjectEntityDTO dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ProjectEntityNotCreatedException(NOT_CREATED, errorMessageBuilder.message(bindingResult));
        }

        ProjectEntity response;
        try {
            Long userId = Long.valueOf(jwtUtil.validateTokenAndRetrieveClaims(token.substring(7)).get("id"));
            response = projectEntityService.save(projectEntityMapper.dotToModel(dto));
            usersProjectRoleService.save(userId, response.getId(), UsersProjectRole.MANAGER);
        } catch (TokenExpiredException e) {
            throw new JWTExpiredException(477, e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(projectEntityMapper.modelToDto(response));
    }

    @GetMapping
    public ResponseEntity<List<ProjectEntityDTO>> index() {

        List<ProjectEntityDTO> listDTOs = projectEntityService.findAll()
                .stream()
                .map(projectEntityMapper::modelToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(listDTOs);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<ProjectEntityDTO> update(@RequestHeader("Authorization") String token,
                                                   @PathVariable(name = "id") Long id,
                                                   @RequestBody @Valid ProjectEntityDTO dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ProjectEntityNotUpdatedException(NOT_UPDATED, errorMessageBuilder.message(bindingResult));
        }

        ProjectEntity response;

        if (usersProjectRoleAccessChecker.checkAccess(token, id, Collections.singletonList(UsersProjectRole.MANAGER))) {
            response = projectEntityService.update(id, projectEntityMapper.dotToModel(dto));
        } else {
            throw new UserAccessDeniedException(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the operation.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(projectEntityMapper.modelToDto(response));
    }

    @PostMapping("/{id}/create-task")
    public ResponseEntity<TaskEntityDTO> createTask(@RequestHeader("Authorization") String token,
                                                    @PathVariable(name = "id") Long id,
                                                    @RequestBody @Valid TaskEntityDTO dto,
                                                    BindingResult bindingResult) throws ProjectEntityNotFoundException {

        if (bindingResult.hasErrors()) {
            throw new TaskEntityNotCreatedException(NOT_CREATED, errorMessageBuilder.message(bindingResult));
        }

        TaskEntity response;

        if (usersProjectRoleAccessChecker.checkAccess(token, id, Arrays.asList(UsersProjectRole.MANAGER, UsersProjectRole.WORKER))) {
            response = projectEntityService.addTask(id, taskEntityMapper.dtoToModel(dto));
        } else {
            throw new UserAccessDeniedException(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the operation.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(taskEntityMapper.modelToDto(response));
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskEntityDTO>> getTasks(@PathVariable(name = "id") Long id) {

        List<TaskEntityDTO> entityDTOs = taskEntityService.getTasksByProjectId(id)
                .stream()
                .map(taskEntityMapper::modelToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(entityDTOs);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,
                                    @PathVariable(name = "id") Long id) {

        if (usersProjectRoleAccessChecker.checkAccess(token, id, Collections.singletonList(UsersProjectRole.MANAGER))) {
            projectEntityService.delete(id);
        } else {
            throw new UserAccessDeniedException(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the operation.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(id);
    }


}
