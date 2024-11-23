package com.pet.study.exception.user;

import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationException extends AuthenticationException {
    public UserAuthenticationException(String message) {
        super(message);
    }
}
