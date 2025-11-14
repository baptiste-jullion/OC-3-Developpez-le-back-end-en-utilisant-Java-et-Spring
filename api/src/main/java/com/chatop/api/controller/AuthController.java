package com.chatop.api.controller;

import com.chatop.api.dto.auth.request.AuthLoginRequestDto;
import com.chatop.api.dto.auth.request.AuthRegisterRequestDto;
import com.chatop.api.dto.auth.response.AuthJwtResponseDto;
import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register")
    public ResponseEntity<AuthJwtResponseDto> register(@RequestBody AuthRegisterRequestDto request) {
        AuthJwtResponseDto result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<AuthJwtResponseDto> login(@RequestBody AuthLoginRequestDto request) {
        AuthJwtResponseDto result = authService.login(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user info")
    public ResponseEntity<UserReadResponseDto> me(Principal principal) {
        String email = principal != null ? principal.getName() : null;
        UserReadResponseDto userDto = authService.me(email);
        if (userDto == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
