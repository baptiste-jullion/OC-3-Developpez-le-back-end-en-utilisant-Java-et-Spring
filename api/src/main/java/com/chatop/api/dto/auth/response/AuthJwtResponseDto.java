package com.chatop.api.dto.auth.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthJwtResponseDto {
    private String token;

    public AuthJwtResponseDto(String token) {
        this.token = token;
    }
}
