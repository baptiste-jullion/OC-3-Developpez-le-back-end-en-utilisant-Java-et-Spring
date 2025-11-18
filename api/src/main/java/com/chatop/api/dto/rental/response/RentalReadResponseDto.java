package com.chatop.api.dto.rental.response;

import com.chatop.api.dto.message.response.MessageReadResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RentalReadResponseDto {
    private Long id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    @JsonProperty("owner_id")
    private Long ownerId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private List<MessageReadResponseDto> messages;
}
