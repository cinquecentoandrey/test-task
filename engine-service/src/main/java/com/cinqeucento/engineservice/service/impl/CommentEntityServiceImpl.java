package com.cinqeucento.engineservice.service.impl;

import com.cinqeucento.engineservice.model.CommentEntity;
import com.cinqeucento.engineservice.repository.CommentEntityRepository;
import com.cinqeucento.engineservice.service.CommentEntityService;
import com.cinqucento.engineservicestarter.exception.ProjectEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentEntityServiceImpl implements CommentEntityService {

    @Value("${entity-error-code.not-found}")
    private Integer NOT_FOUND;
    private final CommentEntityRepository commentEntityRepository;

    @Autowired
    public CommentEntityServiceImpl(CommentEntityRepository commentEntityRepository) {
        this.commentEntityRepository = commentEntityRepository;
    }

    @Override
    @Transactional
    public CommentEntity save(CommentEntity entity) {
        return commentEntityRepository.save(entity);
    }

    @Override
    public List<CommentEntity> findAll() {
        return commentEntityRepository.findAll();
    }

    @Override
    public CommentEntity findById(Long id) {
        Optional<CommentEntity> optEntity = commentEntityRepository.findById(id);

        if (optEntity.isPresent()) {
            return optEntity.get();
        } else {
            throw new ProjectEntityNotFoundException(NOT_FOUND, "Comment with id = " + id + " not found.");
        }
    }

    @Override
    public boolean exist(Long id) {
        return commentEntityRepository.existsById(id);
    }

    @Override
    @Transactional
    public CommentEntity update(Long id, CommentEntity entityToUpdate) {

        if (commentEntityRepository.existsById(id)) {
            entityToUpdate.setId(id);
            return commentEntityRepository.save(entityToUpdate);
        } else {
            throw new ProjectEntityNotFoundException(NOT_FOUND, "Comment with id = " + id + " not found.");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentEntityRepository.deleteById(id);
    }

    @Override
    public List<CommentEntity> getTasksByTaskId(Long taskId) {
        return commentEntityRepository.findAllByTask_Id(taskId);
    }

    @Override
    @Transactional
    public CommentEntity updateById(Long id, CommentEntity entityToUpdate) {
        if (commentEntityRepository.existsById(id)) {
            entityToUpdate.setId(id);
            commentEntityRepository.updateById(id, entityToUpdate.getCommentContent());

            return entityToUpdate;
        } else {
            throw new ProjectEntityNotFoundException(NOT_FOUND, "Comment with id = " + id + " not found.");
        }
    }
}
