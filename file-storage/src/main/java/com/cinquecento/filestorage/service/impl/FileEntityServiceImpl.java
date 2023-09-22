package com.cinquecento.filestorage.service.impl;

import com.cinquecento.filestorage.model.FileEntity;
import com.cinquecento.filestorage.repository.FileEntityRepository;
import com.cinquecento.filestorage.service.FileEntityService;
import com.cinquecento.filestorage.util.exception.FileEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class FileEntityServiceImpl implements FileEntityService {

    private final FileEntityRepository fileEntityRepository;

    @Autowired
    public FileEntityServiceImpl(FileEntityRepository fileEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
    }

    @Override
    public Long save(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileEntity fileEntity = new FileEntity(fileName, file.getBytes(), file.getContentType(), new Date(), new Date());

        return fileEntityRepository.save(fileEntity).getId();
    }

    @Override
    public List<FileEntity> findAll() {
        return fileEntityRepository.findAll();
    }

    @Override
    public FileEntity findById(Long id) {
        Optional<FileEntity> optionalFileEntity = fileEntityRepository.findById(id);

        if (optionalFileEntity.isPresent()) {
            return optionalFileEntity.get();
        } else {
            throw new FileEntityNotFoundException("File with id = " + id + " not found.");
        }

    }

    @Override
    public boolean exist(Long id) {
        return fileEntityRepository.existsById(id);
    }

    @Override
    public void update(Long id, MultipartFile file) throws IOException {
        FileEntity fileEntity = fileEntityRepository.findById(id).get();

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        fileEntity.setFileName(fileName);
        fileEntity.setData(file.getBytes());
        fileEntity.setType(file.getContentType());
        fileEntity.setUpdatedAt(new Date());

        fileEntityRepository.save(fileEntity);
    }

    @Override
    public void delete(Long id) {
        fileEntityRepository.deleteById(id);
    }
}
