package com.pet.study.exception.exception_handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pet.study.dto.CustomApiResponse;
import com.pet.study.exception.auth.EmptyJwtClaimsException;
import com.pet.study.exception.auth.ExpiredJwtTokenException;
import com.pet.study.exception.auth.InvalidJwtSignatureException;
import com.pet.study.exception.auth.InvalidJwtTokenException;
import com.pet.study.exception.auth.RefreshTokenExpiredException;
import com.pet.study.exception.auth.RefreshTokenNotFoundException;
import com.pet.study.exception.auth.UnsupportedJwtTokenException;

@ControllerAdvice
@Order(1)
public class AuthExceptionHandler {

    @ExceptionHandler(EmptyJwtClaimsException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleEmptyJwtClaimsException(EmptyJwtClaimsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtTokenException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleExpiredJwtTokenException(ExpiredJwtTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(InvalidJwtSignatureException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleInvalidJwtSignatureException(InvalidJwtSignatureException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleInvalidJwtTokenException(InvalidJwtTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(UnsupportedJwtTokenException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleUnsupportedJwtTokenException(UnsupportedJwtTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleRefreshTokenNotFoundException(
            RefreshTokenNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleRefreshTokenExpiredException(RefreshTokenExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, ex.getMessage()));
    }
}
