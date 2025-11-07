package com.chatop.api.service;

import com.chatop.api.dto.rentals.RentalCreateRequestDto;
import com.chatop.api.dto.rentals.RentalDto;
import com.chatop.api.dto.rentals.RentalListDto;
import com.chatop.api.dto.rentals.RentalUpdateRequestDto;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.mapper.RentalMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;


    private final FileStorageService fileStorageService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, FileStorageService fileStorageService) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }


    public RentalListDto getAllRentals() {
        List<RentalDto> rentals = rentalRepository.findAll().stream()
                .map(RentalMapper::toDto)
                .collect(Collectors.toList());
        return new RentalListDto(rentals);
    }


    public RentalDto getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        return RentalMapper.toDto(rental);
    }


    @Transactional
    public RentalDto createRental(RentalCreateRequestDto dto, User owner) {
        if (owner == null) {
            throw new IllegalArgumentException("owner must not be null");
        }
        String pictureUrl = null;
        MultipartFile pictureFile = dto.getPicture();
        if (pictureFile != null && !pictureFile.isEmpty()) {
            pictureUrl = fileStorageService.storeFile(pictureFile);
        }
        Rental rental = RentalMapper.fromCreateDto(dto, owner, pictureUrl);
        Rental saved = rentalRepository.save(rental);
        return RentalMapper.toDto(saved);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Transactional
    public RentalDto updateRental(Long id, RentalUpdateRequestDto dto) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        String pictureUrl = null;
        MultipartFile pictureFile = dto.getPicture();
        if (pictureFile != null && !pictureFile.isEmpty()) {
            pictureUrl = fileStorageService.storeFile(pictureFile);
        }
        RentalMapper.updateRentalFromDto(rental, dto, pictureUrl);
        Rental saved = rentalRepository.save(rental);
        return RentalMapper.toDto(saved);
    }

    @Transactional
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
