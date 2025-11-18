package com.chatop.api.mapper;


import com.chatop.api.dto.rental.request.RentalCreateRequestDto;
import com.chatop.api.dto.rental.request.RentalUpdateRequestDto;
import com.chatop.api.dto.rental.response.RentalReadResponseDto;
import com.chatop.api.entity.Rental;
import com.chatop.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    @Mapping(target = "ownerId", source = "rental.owner.id")
    @Mapping(target = "picture", expression = "java(rental.getPicture() != null ? \"/api/files/\" + rental.getPicture() : null)")
    RentalReadResponseDto toReadDto(Rental rental);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "picture", source = "picture")
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "name", source = "rentalCreateResponseDto.name")
    Rental toEntity(RentalCreateRequestDto rentalCreateResponseDto, User owner, String picture);

    void updateEntityFromDto(RentalUpdateRequestDto dto, @MappingTarget Rental rental);
}