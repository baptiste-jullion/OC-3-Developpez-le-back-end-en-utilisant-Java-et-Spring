package com.chatop.api.dto.rental.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalCreateRequestDto {
    private Double price;
    private Double surface;
    private MultipartFile picture;
    private String description;
    private String name;
}
