package com.pet.study.exception.exception_handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pet.study.dto.CustomApiResponse;
import com.pet.study.exception.user.UserAuthenticationException;
import com.pet.study.exception.user.UserNotFoundException;

@ControllerAdvice
@Order(1)
public class UserExceptionHelper {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleUserAuthenticationException(UserAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

}
