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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
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
    public RentalDto createRental(RentalCreateRequestDto dto) {
        User owner = userRepository.findById(dto.getOwnerId()).orElseThrow();
        Rental rental = RentalMapper.fromCreateDto(dto, owner);
        Rental saved = rentalRepository.save(rental);
        return RentalMapper.toDto(saved);
    }


    @Transactional
    public RentalDto updateRental(Long id, RentalUpdateRequestDto dto) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        RentalMapper.updateRentalFromDto(rental, dto);
        Rental saved = rentalRepository.save(rental);
        return RentalMapper.toDto(saved);
    }

    @Transactional
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
