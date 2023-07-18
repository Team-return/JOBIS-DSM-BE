package com.example.jobisapplication.domain.auth.exception.error;

import com.example.jobisapplication.common.error.ErrorProperty;
import com.example.jobisapplication.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorProperty {

    UNVERIFIED_EMAIL(HttpStatus.UNAUTHORIZED, "Unverified Email"),
    BAD_AUTH_CODE(HttpStatus.UNAUTHORIZED, "Bad Auth Code"),

    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh Token Not Found"),
    AUTH_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "AuthCode Not Found");

    private final HttpStatus status;
    private final String message;
}
