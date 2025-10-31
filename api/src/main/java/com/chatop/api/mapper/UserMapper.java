package com.chatop.api.mapper;

import com.chatop.api.dto.RegisterRequestDto;
import com.chatop.api.dto.UserDto;
import com.chatop.api.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {
    public static User toUser(RegisterRequestDto dto, PasswordEncoder encoder) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        return user;
    }
}