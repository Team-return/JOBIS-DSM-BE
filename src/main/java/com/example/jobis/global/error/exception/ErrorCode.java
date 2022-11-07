package com.example.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    EXPIRED_TOKEN(401,"Token Expired" ),
    INVALID_TOKEN(401,"Invalid Token"),

    USER_NOT_FOUND(404, "User Not Found"),

    COMPANY_NOT_FOUND(404, "Company Not Found")

    ;
    private final Integer status;
    private final String message;
}
