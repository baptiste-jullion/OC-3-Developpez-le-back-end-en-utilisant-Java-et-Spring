package com.chatop.api.dto.rental.response;

import com.chatop.api.dto.message.response.MessageReadResponseDto;
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
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MessageReadResponseDto> messages;
}
