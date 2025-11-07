package com.chatop.api.controller;

import com.chatop.api.dto.rentals.RentalCreateRequestDto;
import com.chatop.api.dto.rentals.RentalDto;
import com.chatop.api.dto.rentals.RentalListDto;
import com.chatop.api.dto.rentals.RentalUpdateRequestDto;
import com.chatop.api.model.User;
import com.chatop.api.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<RentalListDto> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<RentalDto> createRental(@ModelAttribute RentalCreateRequestDto dto, Principal principal) {
        String email = principal != null ? principal.getName() : null;
        if (email == null) {
            return ResponseEntity.status(401).build();
        }
        User user = rentalService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(rentalService.createRental(dto, user));
    }


    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<RentalDto> updateRental(@PathVariable Long id, @ModelAttribute RentalUpdateRequestDto dto) {
        return ResponseEntity.ok(rentalService.updateRental(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}
