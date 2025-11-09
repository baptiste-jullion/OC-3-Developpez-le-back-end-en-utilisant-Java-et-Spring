package com.chatop.api.controller;

import com.chatop.api.dto.auth.request.AuthLoginRequestDto;
import com.chatop.api.dto.auth.request.AuthRegisterRequestDto;
import com.chatop.api.dto.auth.response.AuthJwtResponseDto;
import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthJwtResponseDto> register(@RequestBody AuthRegisterRequestDto request) {
        AuthJwtResponseDto result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthJwtResponseDto> login(@RequestBody AuthLoginRequestDto request) {
        AuthJwtResponseDto result = authService.login(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me")
    public ResponseEntity<UserReadResponseDto> me(Principal principal) {
        String email = principal != null ? principal.getName() : null;
        UserReadResponseDto userDto = authService.me(email);
        if (userDto == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
