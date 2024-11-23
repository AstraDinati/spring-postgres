package com.pet.study.exception.auth;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException() {
        super("Refresh token was expired. Please make a new signin request.");
    }

    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
