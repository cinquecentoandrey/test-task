package com.cinqeucento.engineservice.service;

import com.cinqeucento.engineservice.model.CommentEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CommentEntityService {

    CommentEntity save(CommentEntity entity);

    List<CommentEntity> findAll();

    CommentEntity findById(Long id);

    boolean exist(Long id);

    CommentEntity update(Long id, CommentEntity entityToUpdate);

    void delete(Long id);

    List<CommentEntity> getTasksByTaskId(Long id);

    @Transactional
    CommentEntity updateById(Long id, CommentEntity entityToUpdate);
}
