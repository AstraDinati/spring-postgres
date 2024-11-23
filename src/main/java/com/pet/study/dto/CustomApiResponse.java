package com.pet.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public CustomApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
