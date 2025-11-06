package com.chatop.api.controller;

import com.chatop.api.dto.AuthSuccessDto;
import com.chatop.api.dto.LoginRequestDto;
import com.chatop.api.dto.RegisterRequestDto;
import com.chatop.api.dto.UserDto;
import com.chatop.api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthSuccessDto> register(@RequestBody RegisterRequestDto request) {
        AuthSuccessDto result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthSuccessDto> login(@RequestBody LoginRequestDto request) {
        AuthSuccessDto result = authService.login(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Principal principal) {
        String email = principal != null ? principal.getName() : null;
        UserDto userDto = authService.me(email);
        if (userDto == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
