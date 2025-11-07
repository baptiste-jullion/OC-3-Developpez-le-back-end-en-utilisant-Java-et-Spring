package com.chatop.api.dto.rentals;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RentalListDto {
    private List<RentalDto> rentals;

    public RentalListDto(List<RentalDto> rentals) {
        this.rentals = rentals;
    }
}