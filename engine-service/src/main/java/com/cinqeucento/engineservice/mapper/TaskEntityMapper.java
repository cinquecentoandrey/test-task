package com.cinqeucento.engineservice.mapper;

import com.cinqeucento.engineservice.model.TaskEntity;
import com.cinqucento.engineservicestarter.dto.TaskEntityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {

    TaskEntity dtoToModel(TaskEntityDTO dto);

    TaskEntityDTO modelToDto(TaskEntity model);
}
