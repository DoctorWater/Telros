package ru.malkov.telrostesttask.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.malkov.telrostesttask.dto.UserContactInfoDto;
import ru.malkov.telrostesttask.dto.UserDto;
import ru.malkov.telrostesttask.entities.User;

/**
 * Provides mapping of {@link UserContactInfoDto} to {@link User}
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "user_id", ignore = true)
    User toUser(UserDto dto);
}
