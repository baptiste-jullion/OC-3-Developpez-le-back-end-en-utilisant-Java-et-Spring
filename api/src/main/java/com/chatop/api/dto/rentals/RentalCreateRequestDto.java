package com.chatop.api.dto.rentals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalCreateRequestDto {
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Long ownerId;
}