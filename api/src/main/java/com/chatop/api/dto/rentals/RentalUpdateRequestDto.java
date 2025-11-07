package com.chatop.api.dto.rentals;


import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalUpdateRequestDto {
    private String name;
    private Double surface;
    private Double price;
    private MultipartFile picture;
    private String description;
}