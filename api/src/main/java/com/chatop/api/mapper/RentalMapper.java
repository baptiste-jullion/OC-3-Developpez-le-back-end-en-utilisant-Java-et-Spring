package com.chatop.api.mapper;

import com.chatop.api.dto.rentals.RentalCreateRequestDto;
import com.chatop.api.dto.rentals.RentalDto;
import com.chatop.api.dto.rentals.RentalUpdateRequestDto;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {
        if (rental == null) return null;
        RentalDto dto = new RentalDto();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        dto.setPicture(rental.getPicture());
        dto.setDescription(rental.getDescription());
        dto.setOwnerId(rental.getOwner() != null ? rental.getOwner().getId() : null);
        dto.setCreatedAt(rental.getCreatedAt());
        dto.setUpdatedAt(rental.getUpdatedAt());
        return dto;
    }

    public static Rental fromCreateDto(RentalCreateRequestDto dto, User owner) {
        if (dto == null) return null;
        Rental rental = new Rental();
        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setPicture(dto.getPicture());
        rental.setDescription(dto.getDescription());
        rental.setOwner(owner);
        return rental;
    }

    public static void updateRentalFromDto(Rental rental, RentalUpdateRequestDto dto) {
        if (dto.getName() != null) rental.setName(dto.getName());
        if (dto.getSurface() != null) rental.setSurface(dto.getSurface());
        if (dto.getPrice() != null) rental.setPrice(dto.getPrice());
        if (dto.getPicture() != null) rental.setPicture(dto.getPicture());
        if (dto.getDescription() != null) rental.setDescription(dto.getDescription());
    }
}