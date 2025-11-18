package com.chatop.api.dto.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginRequestDto {
    private String login;
    private String password;
}
