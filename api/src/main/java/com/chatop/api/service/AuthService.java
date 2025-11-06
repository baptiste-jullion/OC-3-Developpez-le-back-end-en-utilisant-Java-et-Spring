package com.chatop.api.service;

import com.chatop.api.dto.AuthSuccessDto;
import com.chatop.api.dto.LoginRequestDto;
import com.chatop.api.dto.RegisterRequestDto;
import com.chatop.api.dto.UserDto;
import com.chatop.api.mapper.UserMapper;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey jwtKey;
    private final long jwtExpirationMs;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${jwt.secret}") String jwtSecret,
                       @Value("${jwt.expiration-ms}") long jwtExpirationMs) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtExpirationMs = jwtExpirationMs;
        this.jwtKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public AuthSuccessDto register(RegisterRequestDto request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        User user = UserMapper.toUser(request, passwordEncoder);
        User saved = userRepository.save(user);

        String token = createToken(saved);
        return new AuthSuccessDto(token);
    }

    public AuthSuccessDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getLogin())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = createToken(user);
        return new AuthSuccessDto(token);
    }

    public UserDto me(String email) {
        if (email == null) {
            return null;
        }
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(UserDto::new).orElse(null);
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
