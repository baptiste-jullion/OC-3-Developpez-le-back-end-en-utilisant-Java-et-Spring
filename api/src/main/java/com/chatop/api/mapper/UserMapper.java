package com.chatop.api.mapper;

import com.chatop.api.dto.auth.request.AuthRegisterRequestDto;
import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", qualifiedByName = "hashPassword")
    User toEntity(AuthRegisterRequestDto authRegisterRequestDto);

    UserReadResponseDto toReadDto(User user);

    @Named(value = "hashPassword")
    default String hashPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}