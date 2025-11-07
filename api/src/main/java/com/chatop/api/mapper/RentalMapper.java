package com.chatop.api.mapper;

import com.chatop.api.dto.rentals.RentalCreateRequestDto;
import com.chatop.api.dto.rentals.RentalDto;
import com.chatop.api.dto.rentals.RentalUpdateRequestDto;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {
        if (rental == null) return null;
        RentalDto dto = new RentalDto();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        if (rental.getPicture() != null && !rental.getPicture().isEmpty()) {
            String filePath = "/uploads/" + rental.getPicture();
            String fullUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(filePath).toUriString();
            dto.setPicture(fullUrl);
        } else {
            dto.setPicture(null);
        }
        dto.setDescription(rental.getDescription());
        dto.setOwnerId(rental.getOwner() != null ? rental.getOwner().getId() : null);
        dto.setCreatedAt(rental.getCreatedAt());
        dto.setUpdatedAt(rental.getUpdatedAt());
        return dto;
    }

    public static Rental fromCreateDto(RentalCreateRequestDto dto, User owner, String picturePath) {
        if (dto == null) return null;
        Rental rental = new Rental();
        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setPicture(picturePath);
        rental.setDescription(dto.getDescription());
        rental.setOwner(owner);
        return rental;
    }

    public static void updateRentalFromDto(Rental rental, RentalUpdateRequestDto dto, String picturePath) {
        if (dto.getName() != null) rental.setName(dto.getName());
        if (dto.getSurface() != null) rental.setSurface(dto.getSurface());
        if (dto.getPrice() != null) rental.setPrice(dto.getPrice());
        if (picturePath != null) rental.setPicture(picturePath);
        if (dto.getDescription() != null) rental.setDescription(dto.getDescription());
    }
}