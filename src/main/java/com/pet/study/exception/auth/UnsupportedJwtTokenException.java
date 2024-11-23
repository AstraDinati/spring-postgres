package com.pet.study.exception.auth;

public class UnsupportedJwtTokenException extends RuntimeException {
    public UnsupportedJwtTokenException(String message) {
        super(message);
    }
}
