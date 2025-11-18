package com.chatop.api.dto.message.response;

import com.chatop.api.dto.user.response.UserReadResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageReadResponseDto {
    private UserReadResponseDto user;
    private String message;
    private Long id;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
