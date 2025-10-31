package com.chatop.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String login;
    private String password;

    public LoginRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

