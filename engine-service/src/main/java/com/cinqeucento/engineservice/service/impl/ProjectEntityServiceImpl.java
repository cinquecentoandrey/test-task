package com.cinqeucento.engineservice.service.impl;

import com.cinqeucento.engineservice.model.ProjectEntity;
import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqeucento.engineservice.repository.ProjectEntityRepository;
import com.cinqeucento.engineservice.repository.TaskEntityRepository;
import com.cinqeucento.engineservice.service.ProjectEntityService;
import com.cinqucento.engineservicestarter.exception.ProjectEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProjectEntityServiceImpl implements ProjectEntityService {

    @Value("${entity-error-code.not-found}")
    private Integer NOT_FOUND;

    private final ProjectEntityRepository projectEntityRepository;
    private final TaskEntityRepository taskEntityRepository;

    @Autowired
    public ProjectEntityServiceImpl(ProjectEntityRepository projectEntityRepository,
                                    TaskEntityRepository taskEntityRepository) {
        this.projectEntityRepository = projectEntityRepository;
        this.taskEntityRepository = taskEntityRepository;
    }

    @Override
    @Transactional
    public ProjectEntity save(ProjectEntity entity) {
        return projectEntityRepository.save(entity);
    }

    @Override
    public List<ProjectEntity> findAll() {
        return projectEntityRepository.findAll();
    }

    @Override
    public ProjectEntity findById(Long id) {
        Optional<ProjectEntity> optEntity = projectEntityRepository.findById(id);

        if (optEntity.isPresent()) {
            return optEntity.get();
        } else {
            throw new ProjectEntityNotFoundException(NOT_FOUND, "The project with id = " + id + " was not found.");
        }
    }

    @Override
    public boolean exist(Long id) {
        return projectEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public ProjectEntity update(Long id, ProjectEntity entityToUpdate) {

        if (projectEntityRepository.existsById(id)) {
            entityToUpdate.setId(id);
            return projectEntityRepository.save(entityToUpdate);
        } else {
            throw new ProjectEntityNotFoundException(NOT_FOUND, "The project with id = " + id + " was not found.");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        projectEntityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskEntity addTask(Long id, TaskEntity taskEntity) {
        ProjectEntity projectEntity = this.findById(id);

        projectEntity.getTask().add(taskEntity);
        taskEntity.setProject(projectEntity);

        projectEntityRepository.save(projectEntity);

        return taskEntityRepository.save(taskEntity);
    }

}
