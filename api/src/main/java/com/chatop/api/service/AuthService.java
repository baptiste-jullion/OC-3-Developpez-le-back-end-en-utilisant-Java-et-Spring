package com.chatop.api.service;

import com.chatop.api.dto.auth.request.AuthLoginRequestDto;
import com.chatop.api.dto.auth.request.AuthRegisterRequestDto;
import com.chatop.api.dto.auth.response.AuthJwtResponseDto;
import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.chatop.api.entity.User;
import com.chatop.api.mapper.UserMapper;
import com.chatop.api.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SecretKey jwtKey;

    @Value("${jwt.expiration.ms}")
    private long jwtExpirationMs;


    public AuthJwtResponseDto register(AuthRegisterRequestDto request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);

        String token = createToken(saved);
        return new AuthJwtResponseDto(token);
    }

    public AuthJwtResponseDto login(AuthLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = createToken(user);
        return new AuthJwtResponseDto(token);
    }

    public UserReadResponseDto me(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        return userMapper.toReadDto(user.get());
    }


    private String createToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(jwtKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
