package com.chatop.api.repository;

import com.chatop.api.entity.Rental;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Override
    @EntityGraph(attributePaths = "messages")
    @NonNull
    List<Rental> findAll();

    @Override
    @EntityGraph(attributePaths = "messages")
    @NonNull
    Optional<Rental> findById(@NonNull Long id);
}
