package com.cinquecento.filestorage.service.impl;

import com.cinquecento.exception.FileEntityNotFoundException;
import com.cinquecento.filestorage.model.FileEntity;
import com.cinquecento.filestorage.repository.FileEntityRepository;
import com.cinquecento.filestorage.service.FileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FileEntityServiceImpl implements FileEntityService {

    @Value("${file.upload-path}")
    private String path;

    private final FileEntityRepository fileEntityRepository;

    @Autowired
    public FileEntityServiceImpl(FileEntityRepository fileEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
    }

    @Override
    public FileEntity save(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fullPath = saveInInternalStorage(file , fileName);

        FileEntity fileEntity = new FileEntity(fileName, fullPath, file.getContentType());
        return fileEntityRepository.save(fileEntity);
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
            throw new FileEntityNotFoundException(HttpStatus.NOT_FOUND.value(), "File with id = " + id + " not found.");
        }

    }

    @Override
    public boolean exist(Long id) throws FileEntityNotFoundException {
        return fileEntityRepository.existsById(id) && existInInternalStorage(findById(id).getFileName());
    }

    @Override
    public FileEntity update(Long id, MultipartFile file) throws IOException {
        FileEntity fileEntity = fileEntityRepository.findById(id).get();
        String fullPath = saveInInternalStorage(file, fileEntity.getFileName());

        fileEntity.setFileName(fileEntity.getFileName());
        fileEntity.setPath(fullPath);
        fileEntity.setType(file.getContentType());

        return fileEntityRepository.save(fileEntity);
    }

    @Override
    public void delete(Long id) throws FileEntityNotFoundException {
        FileEntity file = fileEntityRepository.findById(id).get();

        File fileToDelete = new File(file.getPath());
        fileToDelete.delete();

        fileEntityRepository.deleteById(id);
    }

    private String saveInInternalStorage(MultipartFile file, String fileName) throws IOException {
        File uploadDir = new File(path);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fullPath = uploadDir + "/" + fileName;

        file.transferTo(new File(fullPath));

        return fullPath;
    }

    private boolean existInInternalStorage(String filename) {
        File dir = new File(path);
        File[] files = dir.listFiles();

        return Arrays.stream(files)
                .anyMatch(file -> file.isFile() && file.getName().equals(filename));
    }
}
