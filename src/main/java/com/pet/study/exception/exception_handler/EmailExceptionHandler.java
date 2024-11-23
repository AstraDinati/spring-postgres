package com.pet.study.exception.exception_handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pet.study.dto.CustomApiResponse;
import com.pet.study.exception.email.EmailAlreadyTakenException;
import com.pet.study.exception.email.EmailNotFoundException;
import com.pet.study.exception.email.EmailNotVerifiedException;
import com.pet.study.exception.email.EmailSendingException;

@ControllerAdvice
@Order(1)
public class EmailExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleEmailNotFoundException(EmailNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleEmailAlreadyTakenException(EmailAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleEmailNotVerifiedException(EmailNotVerifiedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<CustomApiResponse<Void>> handleEmailSendingException(EmailSendingException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomApiResponse<>(false, e.getMessage()));
    }
}
