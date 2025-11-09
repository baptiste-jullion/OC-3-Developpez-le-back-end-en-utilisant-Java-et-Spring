package com.chatop.api.dto.message.response;

import com.chatop.api.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageReadResponseDto {
    private User user;
    private String message;
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
