package com.chatop.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthSuccessDto {
    private String token;

    public AuthSuccessDto() {
    }

    public AuthSuccessDto(String token) {
        this.token = token;
    }
}

