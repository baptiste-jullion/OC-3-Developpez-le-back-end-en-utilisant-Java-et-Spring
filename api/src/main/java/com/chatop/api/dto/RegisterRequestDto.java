package com.chatop.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String email;
    private String name;
    private String password;

    public RegisterRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}

