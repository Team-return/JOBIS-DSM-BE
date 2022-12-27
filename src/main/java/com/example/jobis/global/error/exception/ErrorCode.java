package com.example.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    BAD_EMAIL(400, "Bad Email Domain"),

    UNVERIFIED_EMAIL(401, "Unverified Email"),
    BAD_AUTH_CODE(401, "Bad Auth Code"),
    EXPIRED_TOKEN(401,"Token Expired" ),
    INVALID_TOKEN(401,"Invalid Token"),
    INVALID_PASSWORD(401, "invalid password"),
    INVALID_CODE(401, "invalid code"),

    USER_NOT_FOUND(404, "User Not Found"),
    COMPANY_NOT_FOUND(404, "Company Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),
    COMPANY_ALREADY_EXISTS(409, "Company Already Exists"),
    MAIL_SEND_FAIL(404, "Mail Send Fail"),
    STUDENT_ALREADY_EXISTS(409, "Student Already Exists")

    ;

    private final Integer status;
    private final String message;
}
