package com.chatop.api.dto.message.response;

import com.chatop.api.dto.user.response.UserReadResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageReadResponseDto {
    private UserReadResponseDto user;
    private String message;
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
