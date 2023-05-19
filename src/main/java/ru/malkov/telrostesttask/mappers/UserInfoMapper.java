package ru.malkov.telrostesttask.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.malkov.telrostesttask.dto.UserContactInfoDto;
import ru.malkov.telrostesttask.entities.User;

/**
 * Provides mapping of {@link UserContactInfoDto} to {@link User}
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserInfoMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "secondName", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    void updateUserFromInfoDto(UserContactInfoDto dto, @MappingTarget User entity);
}
