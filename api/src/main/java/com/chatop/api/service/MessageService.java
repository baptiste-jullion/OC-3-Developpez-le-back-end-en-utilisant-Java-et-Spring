package com.chatop.api.service;

import com.chatop.api.dto.common.ApiResponseDto;
import com.chatop.api.dto.message.request.MessageCreateRequestDto;
import com.chatop.api.dto.message.response.MessageReadResponseDto;
import com.chatop.api.entity.Message;
import com.chatop.api.entity.Rental;
import com.chatop.api.entity.User;
import com.chatop.api.mapper.MessageMapper;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final MessageRepository messagesRepository;
    private final MessageMapper messageMapper;

    public ApiResponseDto<MessageReadResponseDto> create(MessageCreateRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        Rental rental = rentalRepository.findById(dto.getRentalId())
                .orElseThrow(() -> new IllegalArgumentException("Rental does not exist"));

        Message message = messageMapper.toEntity(dto);
        message.setUser(user);
        message.setRental(rental);

        Message savedMessage = messagesRepository.save(message);
        MessageReadResponseDto messageDto = messageMapper.toDto(savedMessage);

        return new ApiResponseDto<>("Message created!", messageDto);
    }
}
