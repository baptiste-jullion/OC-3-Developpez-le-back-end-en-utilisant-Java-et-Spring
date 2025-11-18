package com.chatop.api.mapper;

import com.chatop.api.dto.message.request.MessageCreateRequestDto;
import com.chatop.api.dto.message.response.MessageReadResponseDto;
import com.chatop.api.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageReadResponseDto toDto(Message message);

    Message toEntity(MessageCreateRequestDto dto);
}
