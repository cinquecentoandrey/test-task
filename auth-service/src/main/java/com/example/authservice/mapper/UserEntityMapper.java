package com.example.authservice.mapper;


import com.cinquecento.authservicestarter.dto.RegistrationUserEntityDTO;
import com.cinquecento.authservicestarter.dto.UserEntityDTO;
import com.example.authservice.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntityDTO entityToDto(UserEntity entity);

    UserEntity registrationDtoToEntity(RegistrationUserEntityDTO dto);
}
