package com.gym.gym_application.mapper;

import com.gym.gym_application.dto.UserRegisterRequest;
import com.gym.gym_application.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", constant = "USER")
    User mapFromRegisterDto(UserRegisterRequest request);
}
