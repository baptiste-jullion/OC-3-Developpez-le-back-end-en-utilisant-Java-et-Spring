package com.chatop.api.dto.rental.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalUpdateRequestDto {
    private Double price;
    private Double surface;
    private String description;
    private String name;
}
