package com.cinqeucento.engineservice.service;

import com.cinqeucento.engineservice.model.ProjectEntity;
import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqucento.engineservicestarter.exception.ProjectEntityNotFoundException;
import com.cinqucento.engineservicestarter.exception.ProjectEntityNotUpdatedException;

import java.util.List;

public interface ProjectEntityService {

    ProjectEntity save(ProjectEntity entity);

    List<ProjectEntity> findAll();

    ProjectEntity findById(Long id) throws ProjectEntityNotFoundException;

    boolean exist(Long id);

    ProjectEntity update(Long id, ProjectEntity entityToUpdate) throws ProjectEntityNotUpdatedException;

    void delete(Long id);

    TaskEntity addTask(Long id, TaskEntity taskEntity);

}
