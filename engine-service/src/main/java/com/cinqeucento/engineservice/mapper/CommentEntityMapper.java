package com.cinqeucento.engineservice.mapper;

import com.cinqeucento.engineservice.model.CommentEntity;
import com.cinqucento.engineservicestarter.dto.CommentEntityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEntityMapper {

    CommentEntity dtoToModel(CommentEntityDTO dto);

    CommentEntityDTO modelToDto(CommentEntity model);
}
