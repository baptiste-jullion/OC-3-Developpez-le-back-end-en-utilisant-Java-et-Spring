package com.chatop.api.service;

import com.chatop.api.dto.common.ApiResponseDto;
import com.chatop.api.dto.rental.request.RentalCreateRequestDto;
import com.chatop.api.dto.rental.request.RentalUpdateRequestDto;
import com.chatop.api.dto.rental.response.RentalListResponseDto;
import com.chatop.api.dto.rental.response.RentalReadResponseDto;
import com.chatop.api.entity.Rental;
import com.chatop.api.entity.User;
import com.chatop.api.mapper.RentalMapper;
import com.chatop.api.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final FileStorageService fileStorageService;
    private final RentalMapper rentalMapper;

    public RentalListResponseDto list() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalReadResponseDto> rentalDtos = rentals.stream()
                .map(rentalMapper::toReadDto)
                .toList();
        return new RentalListResponseDto(rentalDtos);
    }


    public RentalReadResponseDto retrieve(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        return rentalMapper.toReadDto(rental);
    }

    public ApiResponseDto<RentalReadResponseDto> create(RentalCreateRequestDto dto, User owner) {
        if (owner == null) {
            throw new IllegalArgumentException("owner must not be null");
        }

        String pictureName = storePictureAndGetName(dto.getPicture());

        Rental rental = rentalMapper.toEntity(dto, owner, pictureName);
        Rental saved = rentalRepository.save(rental);
        RentalReadResponseDto rentalDto = rentalMapper.toReadDto(saved);

        return new ApiResponseDto<>("Rental created !", rentalDto);
    }

    @Transactional
    public ApiResponseDto<RentalReadResponseDto> update(Long id, RentalUpdateRequestDto dto) {
        Rental existingRental = rentalRepository.findById(id).orElseThrow();

        rentalMapper.updateEntityFromDto(dto, existingRental);
        Rental saved = rentalRepository.save(existingRental);
        RentalReadResponseDto rentalDto = rentalMapper.toReadDto(saved);

        return new ApiResponseDto<>("Rental updated !", rentalDto);
    }

    public void delete(Long id) {
        rentalRepository.deleteById(id);
    }

    private String storePictureAndGetName(MultipartFile pictureFile) {
        if (pictureFile != null && !pictureFile.isEmpty()) {
            return fileStorageService.storeFile(pictureFile);
        }
        return null;
    }
}
