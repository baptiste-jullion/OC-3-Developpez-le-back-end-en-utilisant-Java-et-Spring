package com.chatop.api.dto;

import lombok.Data;

@Data
public class AuthSuccessDto {
    private String token;

    public AuthSuccessDto(String token) {
        this.token = token;
    }
}

