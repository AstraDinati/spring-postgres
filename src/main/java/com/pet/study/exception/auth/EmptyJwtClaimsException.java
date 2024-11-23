package com.pet.study.exception.auth;

public class EmptyJwtClaimsException extends RuntimeException {
    public EmptyJwtClaimsException(String message) {
        super(message);
    }
}
