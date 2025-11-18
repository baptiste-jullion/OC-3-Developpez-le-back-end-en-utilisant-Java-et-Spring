package com.chatop.api.controller;

import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserReadResponseDto> getUserById(@PathVariable Long id) {
        UserReadResponseDto userDto = userService.getUserReadDtoById(id);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }
}
