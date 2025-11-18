package com.chatop.api.controller;

import com.chatop.api.dto.common.ApiResponseDto;
import com.chatop.api.dto.message.request.MessageCreateRequestDto;
import com.chatop.api.dto.message.response.MessageReadResponseDto;
import com.chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Tag(name = "Messages")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Create a new message on a rental")
    public ResponseEntity<ApiResponseDto<MessageReadResponseDto>> create(@RequestBody MessageCreateRequestDto request) {
        return ResponseEntity.ok(messageService.create(request));
    }
}
