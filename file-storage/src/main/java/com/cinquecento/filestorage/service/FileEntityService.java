package com.cinquecento.filestorage.service;

import com.cinquecento.exception.FileEntityNotFoundException;
import com.cinquecento.filestorage.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileEntityService {

    FileEntity save(MultipartFile file) throws IOException;

    List<FileEntity> findAll();

    FileEntity findById(Long id);

    boolean exist(Long id);

    FileEntity update(Long id, MultipartFile file) throws IOException;

    void delete(Long id) throws FileEntityNotFoundException;
}
