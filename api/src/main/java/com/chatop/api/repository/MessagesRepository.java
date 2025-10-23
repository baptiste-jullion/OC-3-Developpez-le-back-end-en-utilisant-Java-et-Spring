package com.chatop.api.repository;

import com.chatop.api.model.Message;
import com.chatop.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByUser(User user);

}