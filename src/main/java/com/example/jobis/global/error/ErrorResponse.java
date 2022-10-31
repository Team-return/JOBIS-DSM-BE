package com.example.jobis.global.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final Integer status;
    private final String message;

    @Builder
    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
