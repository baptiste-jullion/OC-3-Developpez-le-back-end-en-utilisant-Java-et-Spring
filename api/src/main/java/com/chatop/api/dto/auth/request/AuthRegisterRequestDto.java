package com.chatop.api.dto.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegisterRequestDto {
    private String email;
    private String name;
    private String password;
}
