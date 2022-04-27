package com.devmode.clientservice.auth.mapper;

import com.devmode.clientservice.auth.api.UserDto;
import com.devmode.clientservice.auth.api.UserInfo;
import com.devmode.clientservice.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    UserDto mapToDto(User user);

    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "id", ignore = true)
    User mapToModel(UserInfo userInfo);

}
