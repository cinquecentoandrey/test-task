package com.cinquecento.filestorage.mapper;

import com.cinquecento.dto.FileEntityDTO;
import com.cinquecento.filestorage.model.FileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper {

    FileEntity dtoToModel(FileEntityDTO dto);

    FileEntityDTO modelToDto(FileEntity model);
}
