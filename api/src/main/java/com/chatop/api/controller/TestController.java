package com.chatop.api.controller;

import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/test")
    public String testEndpoint() {
        return "Félicitations, le serveur Spring Boot répond !";
    }

    @GetMapping("/api/test/db")
    public String testDbConnection() {
        Optional<User> user = userRepository.findByEmail("toto@toto.toot");
        // JSON of the user if exists, else return "User not found"
        return user.map(User::toString).orElse("User not found");
    }
}