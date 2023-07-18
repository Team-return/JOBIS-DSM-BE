package com.example.jobisapplication.domain.user.exception.error;

import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.jobisapplication.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorProperty {

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "invalid password"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found");

    private final HttpStatus status;
    private final String message;
}
