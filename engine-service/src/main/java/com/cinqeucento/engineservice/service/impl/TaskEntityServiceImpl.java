package com.cinqeucento.engineservice.service.impl;

import com.cinqeucento.engineservice.model.*;
import com.cinqeucento.engineservice.repository.CommentEntityRepository;
import com.cinqeucento.engineservice.repository.TaskEntityRepository;
import com.cinqeucento.engineservice.service.TaskEntityService;
import com.cinqucento.engineservicestarter.dto.TaskEntityDTO;
import com.cinqucento.engineservicestarter.exception.TaskEntityNotFoundException;
import com.cinqucento.engineservicestarter.model.CurrentTaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TaskEntityServiceImpl implements TaskEntityService {

    @Value("${entity-error-code.not-found}")
    private Integer NOT_FOUND;

    private final TaskEntityRepository taskEntityRepository;
    private final CommentEntityRepository commentEntityRepository;
    private final UsersProjectRoleServiceImpl usersProjectRoleService;

    @Autowired
    public TaskEntityServiceImpl(TaskEntityRepository taskEntityRepository,
                                 CommentEntityRepository commentEntityRepository,
                                 UsersProjectRoleServiceImpl usersProjectRoleService) {
        this.taskEntityRepository = taskEntityRepository;
        this.commentEntityRepository = commentEntityRepository;
        this.usersProjectRoleService = usersProjectRoleService;
    }

    @Override
    @Transactional
    public TaskEntity save(TaskEntity entity) {
        return taskEntityRepository.save(entity);
    }

    @Override
    public List<TaskEntity> findAll() {
        return taskEntityRepository.findAll();
    }

    @Override
    public TaskEntity findById(Long id) {
        Optional<TaskEntity> optTaskEntity = taskEntityRepository.findById(id);

        if (optTaskEntity.isPresent()) {
            return optTaskEntity.get();
        } else {
            throw new TaskEntityNotFoundException(NOT_FOUND, "The task with id = " + id + " was not found.");
        }

    }

    @Override
    public boolean exist(Long id) {
        return taskEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public TaskEntity update(Long id, TaskEntity entityToUpdate) {

        if (taskEntityRepository.existsById(id)) {
            entityToUpdate.setId(id);
            return taskEntityRepository.save(entityToUpdate);
        } else {
            throw new TaskEntityNotFoundException(NOT_FOUND, "The task with id = " + id + " was not found.");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskEntityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CommentEntity addComment(Long id, CommentEntity commentEntity) {
        TaskEntity taskEntity = this.findById(id);

        taskEntity.getComments().add(commentEntity);
        commentEntity.setTask(taskEntity);

        taskEntityRepository.save(taskEntity);

        return commentEntityRepository.save(commentEntity);
    }

    @Override
    public List<TaskEntity> getTasksByProjectId(Long projectId) {
        return taskEntityRepository.findAllByProject_Id(projectId);
    }

    @Override
    @Transactional
    public TaskEntity updateById(Long id, TaskEntity entityToUpdate) {

        if (taskEntityRepository.existsById(id)) {
            taskEntityRepository.updateById(id, entityToUpdate.getTaskName(), entityToUpdate.getTaskDescription());
            entityToUpdate.setId(id);

            return entityToUpdate;
        } else {
            throw new TaskEntityNotFoundException(NOT_FOUND, "The task with id = " + id + " was not found.");
        }

    }

    @Override
    public Page<TaskEntity> findByStatusAndProject(CurrentTaskStatus status, ProjectEntity project, Pageable pageable) {
        return taskEntityRepository.findByTaskStatusAndProject(status, project, pageable);
    }

    @Override
    @Transactional
    public TaskEntity updateStatusById(Long taskId, CurrentTaskStatus status) throws TaskEntityNotFoundException{
        TaskEntity entity = this.findById(taskId);

        entity.setTaskStatus(status);
        entity.setLastStatusUpdate(LocalDateTime.now());

        return taskEntityRepository.save(entity);
    }

    @Override
    public ProjectEntity getProjectByTaskId(Long taskId) throws TaskEntityNotFoundException{
        return this.findById(taskId).getProject();
    }

    @Override
    public Page<TaskEntityDTO> getPageableTasks(Long userId, Long projectId, Integer page, Integer size, CurrentTaskStatus status, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        List<UsersProjectRole> roles = usersProjectRoleService
                .findUserProjectRoleByUserIdAndProjectId(userId, projectId)
                .stream()
                .map(UsersProjectRoleEntity::getUsersProjectRole)
                .collect(Collectors.toList());

        return taskEntityRepository.findFilteredTasks(1L, projectId, status, roles, pageable);
    }

}
