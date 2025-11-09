package com.chatop.api.dto.rental.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RentalListResponseDto {
    private List<RentalReadResponseDto> rentals;

    public RentalListResponseDto(List<RentalReadResponseDto> rentals) {
        this.rentals = rentals;
    }
}
