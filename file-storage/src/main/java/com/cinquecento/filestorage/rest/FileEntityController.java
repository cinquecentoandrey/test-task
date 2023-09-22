package com.cinquecento.filestorage.rest;

import com.cinquecento.filestorage.dto.FileEntityDTO;
import com.cinquecento.filestorage.mapper.FileEntityMapper;
import com.cinquecento.filestorage.service.impl.FileEntityServiceImpl;
import com.cinquecento.filestorage.util.exception.FileEntityNotFoundException;
import com.cinquecento.filestorage.util.exception.FileEntityNotSavedException;
import com.cinquecento.filestorage.util.exception.FileEntityNotUpdatedException;
import com.cinquecento.filestorage.util.response.FileEntityReferenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String, Long>> upload(@RequestParam(name = "file") MultipartFile file) {

        Long id;

        try {
            id = fileEntityService.save(file);
        } catch (IOException e) {
            throw new FileEntityNotSavedException(e.getMessage());
        }

        Map<String, Long> response = new HashMap<>();
        response.put("ID", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
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
    public ResponseEntity<byte[]> get(@PathVariable(name = "id") Long id) throws FileEntityNotFoundException {
        FileEntityDTO fileEntity = fileEntityMapper.modelToDto(fileEntityService.findById(id));

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() +"\"")
                .body(fileEntity.getData());
    }

    @PatchMapping("/files/{id}")
    public ResponseEntity<Map<String, Long>> update(@PathVariable(name = "id") Long id,
                                                    MultipartFile file) {

        if (!fileEntityService.exist(id)) {
            throw new FileEntityNotFoundException("File with id = " + id + " not found.");
        } else {
            try {
                fileEntityService.update(id, file);
            } catch (IOException e) {
                throw new FileEntityNotUpdatedException(e.getMessage());
            }
        }

        Map<String, Long> response = new HashMap<>();
        response.put("ID", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<Map<String, Long>> delete(@PathVariable(name = "id") Long id) {
        fileEntityService.delete(id);

        Map<String, Long> response = new HashMap<>();
        response.put("ID", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
