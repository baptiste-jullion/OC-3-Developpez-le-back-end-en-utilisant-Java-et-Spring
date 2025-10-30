package com.chatop.api.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String email;
    private String name;
    private String password;
}

