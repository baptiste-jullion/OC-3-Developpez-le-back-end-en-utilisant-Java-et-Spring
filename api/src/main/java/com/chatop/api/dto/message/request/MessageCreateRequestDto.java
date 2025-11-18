package com.chatop.api.dto.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreateRequestDto {
    private String message;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("rental_id")
    private Long rentalId;
}
