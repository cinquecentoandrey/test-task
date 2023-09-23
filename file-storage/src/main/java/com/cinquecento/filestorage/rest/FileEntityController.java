package com.cinquecento.filestorage.rest;

import com.cinquecento.filestorage.dto.FileEntityDTO;
import com.cinquecento.filestorage.exception.FileEntityNotDeletedException;
import com.cinquecento.filestorage.mapper.FileEntityMapper;
import com.cinquecento.filestorage.service.impl.FileEntityServiceImpl;
import com.cinquecento.filestorage.exception.FileEntityNotFoundException;
import com.cinquecento.filestorage.exception.FileEntityNotSavedException;
import com.cinquecento.filestorage.exception.FileEntityNotUpdatedException;
import com.cinquecento.filestorage.dto.FileEntityReferenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file-storage")
public class FileEntityController {

    private final FileEntityServiceImpl fileEntityService;
    private final FileEntityMapper fileEntityMapper;

    @Autowired
    public FileEntityController(FileEntityServiceImpl fileEntityService, FileEntityMapper fileEntityMapper) {
        this.fileEntityService = fileEntityService;
        this.fileEntityMapper = fileEntityMapper;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileEntityDTO> upload(@RequestParam(name = "file") MultipartFile file) {

        FileEntityDTO dto;

        try {
            dto = fileEntityMapper.modelToDto(fileEntityService.save(file));
        } catch (IOException e) {
            throw new FileEntityNotSavedException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileEntityReferenceResponse>> get() {
        List<FileEntityReferenceResponse> response =
                fileEntityService.findAll()
                        .stream()
                        .map(fileEntity -> {
                            String uri = ServletUriComponentsBuilder
                                    .fromCurrentContextPath()
                                    .path("/files/")
                                    .path(fileEntity.getId().toString())
                                    .toUriString();

                            return new FileEntityReferenceResponse(
                                    fileEntity.getFileName(),
                                    uri,
                                    fileEntity.getType()
                            );

                        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<String> get(@PathVariable(name = "id") Long id) throws FileEntityNotFoundException {
        FileEntityDTO fileEntity = fileEntityMapper.modelToDto(fileEntityService.findById(id));

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() +"\"")
                .body(fileEntity.getPath());
    }

    @PatchMapping("/files/{id}")
    public ResponseEntity<FileEntityDTO> update(@PathVariable(name = "id") Long id,
                                                    MultipartFile file) {
        FileEntityDTO dto;

        if (!fileEntityService.exist(id)) {
            throw new FileEntityNotFoundException(HttpStatus.NOT_FOUND.value(), "File with id = " + id + " not found.");
        } else {
            try {
                dto = fileEntityMapper.modelToDto(fileEntityService.update(id, file));
            } catch (IOException e) {
                throw new FileEntityNotUpdatedException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {

        try {
            fileEntityService.delete(id);
        } catch (FileEntityNotFoundException e) {
            throw new FileEntityNotDeletedException(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
