package com.chatop.api.dto.rentals;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalCreateRequestDto {
    private String name;
    private Double surface;
    private Double price;
    private MultipartFile picture;
    private String description;
}
