package team.retum.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorProperty {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Token Expired"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),

    MAIL_SEND_FAIL(HttpStatus.NOT_FOUND, "Mail Send Fail");

    private final HttpStatus status;
    private final String message;
}
