package com.cinqeucento.engineservice.rest;

import com.cinqucento.engineservicestarter.dto.CommentEntityDTO;
import com.cinqucento.engineservicestarter.dto.TaskEntityDTO;
import com.cinqucento.engineservicestarter.exception.*;
import com.cinqeucento.engineservice.mapper.CommentEntityMapper;
import com.cinqeucento.engineservice.mapper.TaskEntityMapper;
import com.cinqeucento.engineservice.model.CommentEntity;
import com.cinqucento.engineservicestarter.model.CurrentTaskStatus;
import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqeucento.engineservice.model.UsersProjectRole;
import com.cinqeucento.engineservice.service.impl.CommentEntityServiceImpl;
import com.cinqeucento.engineservice.service.impl.TaskEntityServiceImpl;
import com.cinqeucento.engineservice.util.ErrorMessageBuilder;
import com.cinqeucento.engineservice.util.UsersProjectRoleAccessChecker;
import com.cinquecento.jwtstarter.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskEntityController {

    @Value("${entity-error-code.not-created}")
    private Integer NOT_CREATED;
    @Value("${entity-error-code.not-updated}")
    private Integer NOT_UPDATED;

    @Value("${entity-error-code.illegal-arg}")
    private Integer ILLEGAL_ARG;

    @Value("${entity-error-code.access-denied}")
    private Integer ACCESS_DENIED;

    private final TaskEntityServiceImpl taskEntityService;
    private final CommentEntityServiceImpl commentEntityService;
    private final TaskEntityMapper taskEntityMapper;
    private final ErrorMessageBuilder errorMessageBuilder;
    private final CommentEntityMapper commentEntityMapper;
    private final UsersProjectRoleAccessChecker usersProjectRoleAccessChecker;
    private final JWTUtil jwtUtil;

    @Autowired
    public TaskEntityController(TaskEntityServiceImpl taskEntityService,
                                CommentEntityServiceImpl commentEntityService,
                                TaskEntityMapper taskEntityMapper,
                                ErrorMessageBuilder errorMessageBuilder,
                                CommentEntityMapper commentEntityMapper,
                                UsersProjectRoleAccessChecker usersProjectRoleAccessChecker,
                                JWTUtil jwtUtil) {
        this.taskEntityService = taskEntityService;
        this.commentEntityService = commentEntityService;
        this.taskEntityMapper = taskEntityMapper;
        this.errorMessageBuilder = errorMessageBuilder;
        this.commentEntityMapper = commentEntityMapper;
        this.usersProjectRoleAccessChecker = usersProjectRoleAccessChecker;

        this.jwtUtil = jwtUtil;
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<TaskEntityDTO> update(@RequestHeader("Authorization") String token,
                                                @PathVariable(name = "id") Long id,
                                                @RequestBody @Valid TaskEntityDTO dto,
                                                BindingResult bindingResult) throws TaskEntityNotFoundException {

        if (bindingResult.hasErrors()) {
            throw new TaskEntityNotUpdatedException(NOT_UPDATED, errorMessageBuilder.message(bindingResult));
        }

        TaskEntity response;
        Long projectId = taskEntityService.getProjectByTaskId(id).getId();

        if (usersProjectRoleAccessChecker.checkAccess(token, projectId, Arrays.asList(UsersProjectRole.MANAGER,
                UsersProjectRole.WORKER, UsersProjectRole.TESTER))) {
            response = taskEntityService.updateById(id, taskEntityMapper.dtoToModel(dto));
        } else {
            throw new UserAccessDeniedException(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the operation.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(taskEntityMapper.modelToDto(response));
    }

    @PatchMapping("/{id}/update-status")
    public ResponseEntity<TaskEntityDTO> updateStatus(@RequestHeader("Authorization") String token,
                                                      @PathVariable(name = "id") Long id,
                                                      @RequestParam(name = "status") CurrentTaskStatus status) {

        TaskEntityDTO response;
        Long projectId = taskEntityService.getProjectByTaskId(id).getId();

        if (usersProjectRoleAccessChecker.checkAccess(token, projectId, Collections.singletonList(UsersProjectRole.MANAGER))) {
            response = taskEntityMapper.modelToDto(taskEntityService.updateStatusById(id, status));
        } else {
            throw new UserAccessDeniedException(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the operation.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/{id}/add-comment")
    public ResponseEntity<CommentEntityDTO> createTask(@PathVariable(name = "id") Long id,
                                                       @RequestBody @Valid CommentEntityDTO dto,
                                                       BindingResult bindingResult) throws TaskEntityNotFoundException {

        if (bindingResult.hasErrors()) {
            throw new TaskEntityNotCreatedException(NOT_CREATED, errorMessageBuilder.message(bindingResult));
        }

        CommentEntity response = taskEntityService.addComment(id, commentEntityMapper.dtoToModel(dto));

        return ResponseEntity.status(HttpStatus.OK)
                .body(commentEntityMapper.modelToDto(response));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentEntityDTO>> getTasks(@PathVariable(name = "id") Long id) {

        List<CommentEntityDTO> entityDTOs = commentEntityService.getTasksByTaskId(id)
                .stream()
                .map(commentEntityMapper::modelToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(entityDTOs);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,
                                    @PathVariable(name = "id") Long id) {

        Long projectId = taskEntityService.getProjectByTaskId(id).getId();

        if (usersProjectRoleAccessChecker.checkAccess(token, projectId, Collections.singletonList(UsersProjectRole.MANAGER))) {
            taskEntityService.delete(id);
        } else {
            throw new UserAccessDeniedException(HttpStatus.FORBIDDEN.value(), "Insufficient rights to perform the operation.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(id);
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<TaskEntityDTO>> getTasks(@RequestHeader("Authorization") String token,
                                                        @RequestParam(name = "project_id") Long projectId,
                                                        @RequestParam("status") CurrentTaskStatus status,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "25") int size,
                                                        @RequestParam(value = "sort", defaultValue = "id,asc") String sort) {
        String[] sortParams = sort.split(",");
        String sortBy = sortParams[0];
        String sortDirection = sortParams[1];

        if (Arrays.stream(TaskEntityDTO.class.getDeclaredFields())
                .anyMatch(field -> field.toString().equalsIgnoreCase(sortBy))) {
            throw new IllegalSortArgumentException(ILLEGAL_ARG, "Illegal argument: " + sortBy);
        }

        if (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
            throw new IllegalSortDirection(ILLEGAL_ARG, "Illegal sort direction: " + sortDirection);
        }

        Long userId;
        try {
            userId = Long.valueOf(jwtUtil.validateTokenAndRetrieveClaims(token).get("id"));
        } catch (Exception e) {
            throw new JWTExpiredException(ACCESS_DENIED, e.getMessage());
        }

        Page<TaskEntityDTO> tasks = taskEntityService.getPageableTasks(userId, projectId, page, size, status, sortBy, sortDirection);

        return ResponseEntity.status(HttpStatus.OK)
                .body(tasks.toList());
    }
}
