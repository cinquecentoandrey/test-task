package com.cinqeucento.engineservice.mapper;

import com.cinqeucento.engineservice.model.ProjectEntity;
import com.cinqucento.engineservicestarter.dto.ProjectEntityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectEntityMapper {

    ProjectEntity dotToModel(ProjectEntityDTO dto);

    ProjectEntityDTO modelToDto(ProjectEntity model);
}
