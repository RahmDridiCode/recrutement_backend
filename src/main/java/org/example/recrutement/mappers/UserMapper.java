package org.example.recrutement.mappers;

import org.example.recrutement.dto.RestUserDTO;
import org.example.recrutement.dto.UserDTO;
import org.example.recrutement.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO dto);
    User toEntity(RestUserDTO dto);
}
