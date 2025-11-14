package com.chatop.api.controller;

import com.chatop.api.dto.common.ApiResponseDto;
import com.chatop.api.dto.rental.request.RentalCreateRequestDto;
import com.chatop.api.dto.rental.request.RentalUpdateRequestDto;
import com.chatop.api.dto.rental.response.RentalListResponseDto;
import com.chatop.api.dto.rental.response.RentalReadResponseDto;
import com.chatop.api.entity.User;
import com.chatop.api.service.RentalService;
import com.chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
@Tag(name = "Rentals")
public class RentalController {
    private final RentalService rentalService;
    private final UserService userService;

    @PostMapping(consumes = {"multipart/form-data"})
    @Operation(summary = "Create a new rental")
    public ResponseEntity<ApiResponseDto<RentalReadResponseDto>> create(@ModelAttribute RentalCreateRequestDto request, Principal principal) {
        User user = userService.getAuthenticatedUser(principal);
        return ResponseEntity.ok(rentalService.create(request, user));
    }

    @GetMapping
    @Operation(summary = "List all rentals")
    public ResponseEntity<RentalListResponseDto> list() {
        return ResponseEntity.ok(rentalService.list());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a rental by ID")
    public ResponseEntity<RentalReadResponseDto> retrieve(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.retrieve(id));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @Operation(summary = "Update an existing rental")
    public ResponseEntity<ApiResponseDto<RentalReadResponseDto>> update(@PathVariable Long id, @ModelAttribute RentalUpdateRequestDto request) {
        return ResponseEntity.ok(rentalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a rental by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rentalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
