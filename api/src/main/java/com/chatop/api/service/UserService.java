package com.chatop.api.service;

import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.entity.User;
import com.chatop.api.mapper.UserMapper;
import com.chatop.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getAuthenticatedUser(Principal principal) {
        String email = principal != null ? principal.getName() : null;
        if (email == null) {
            return null;
        }
        return getUserByEmail(email);
    }

    public UserReadResponseDto getUserReadDtoById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return userMapper.toReadDto(user);
    }
}
