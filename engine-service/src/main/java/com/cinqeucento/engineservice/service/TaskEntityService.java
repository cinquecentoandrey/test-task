package com.cinqeucento.engineservice.service;

import com.cinqeucento.engineservice.model.CommentEntity;
import com.cinqucento.engineservicestarter.model.CurrentTaskStatus;
import com.cinqeucento.engineservice.model.ProjectEntity;
import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqucento.engineservicestarter.dto.TaskEntityDTO;
import com.cinqucento.engineservicestarter.exception.TaskEntityNotFoundException;
import com.cinqucento.engineservicestarter.exception.TaskEntityNotUpdatedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskEntityService {

    TaskEntity save(TaskEntity entity);

    List<TaskEntity> findAll();

    TaskEntity findById(Long id) throws TaskEntityNotFoundException;

    boolean exist(Long id);

    TaskEntity update(Long id, TaskEntity entityToUpdate) throws TaskEntityNotUpdatedException;

    void delete(Long id);

    CommentEntity addComment(Long id, CommentEntity commentEntity);

    List<TaskEntity> getTasksByProjectId(Long projectId);

    TaskEntity updateById(Long id, TaskEntity entityToUpdate);

    Page<TaskEntity> findByStatusAndProject(CurrentTaskStatus status, ProjectEntity project, Pageable pageable);

    TaskEntity updateStatusById(Long taskId, CurrentTaskStatus status);

    ProjectEntity getProjectByTaskId(Long taskId);

    Page<TaskEntityDTO> getPageableTasks(Long userId, Long projectId, Integer page, Integer size, CurrentTaskStatus status, String sortBy, String sortDirection);
}
