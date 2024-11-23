package com.pet.study.exception.auth;

public class InvalidJwtSignatureException extends RuntimeException {
    public InvalidJwtSignatureException(String message) {
        super(message);
    }
}
