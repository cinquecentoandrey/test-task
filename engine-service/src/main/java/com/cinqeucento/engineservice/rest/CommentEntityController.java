package com.cinqeucento.engineservice.rest;

import com.cinqeucento.engineservice.mapper.CommentEntityMapper;
import com.cinqeucento.engineservice.model.CommentEntity;
import com.cinqeucento.engineservice.service.impl.CommentEntityServiceImpl;
import com.cinqeucento.engineservice.util.ErrorMessageBuilder;
import com.cinqucento.engineservicestarter.dto.CommentEntityDTO;
import com.cinqucento.engineservicestarter.exception.CommentEntityNotUpdatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentEntityController {

    @Value("${entity-error-code.not-updated}")
    private Integer NOT_UPDATED;
    private final CommentEntityServiceImpl commentEntityService;
    private final CommentEntityMapper commentEntityMapper;
    private final ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public CommentEntityController(CommentEntityServiceImpl commentEntityService,
                                   CommentEntityMapper commentEntityMapper,
                                   ErrorMessageBuilder errorMessageBuilder) {
        this.commentEntityService = commentEntityService;
        this.commentEntityMapper = commentEntityMapper;
        this.errorMessageBuilder = errorMessageBuilder;
    }

    @GetMapping
    public ResponseEntity<List<CommentEntityDTO>> index() {

        List<CommentEntityDTO> listDTOs = commentEntityService.findAll()
                .stream()
                .map(commentEntityMapper::modelToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(listDTOs);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<CommentEntityDTO> update(@PathVariable(name = "id") Long id,
                                                   @RequestBody @Valid CommentEntityDTO dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CommentEntityNotUpdatedException(NOT_UPDATED, errorMessageBuilder.message(bindingResult));
        }

        CommentEntity response = commentEntityService.updateById(id, commentEntityMapper.dtoToModel(dto));

        return ResponseEntity.status(HttpStatus.OK)
                .body(commentEntityMapper.modelToDto(response));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        commentEntityService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(id);
    }
}
