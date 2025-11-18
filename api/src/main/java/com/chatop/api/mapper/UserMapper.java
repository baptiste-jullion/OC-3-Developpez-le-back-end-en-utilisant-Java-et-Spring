package com.chatop.api.mapper;

import com.chatop.api.dto.auth.request.AuthRegisterRequestDto;
import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(AuthRegisterRequestDto authRegisterRequestDto);

    UserReadResponseDto toReadDto(User user);
}