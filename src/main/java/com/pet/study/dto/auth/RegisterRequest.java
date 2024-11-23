package com.pet.study.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
