package com.chatop.api.repository;

import com.chatop.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Indique à Spring que cette interface est un bean de type Repository.
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA va automatiquement comprendre cette méthode grâce à son nom !
    // Il va générer la requête SQL "SELECT * FROM USERS WHERE email = ?" pour nous.
    Optional<User> findByEmail(String email);

}