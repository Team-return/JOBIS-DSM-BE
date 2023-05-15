package team.returm.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorProperty {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    EXPIRED_TOKEN(401, "Token Expired"),
    INVALID_TOKEN(401, "Invalid Token"),

    MAIL_SEND_FAIL(404, "Mail Send Fail");

    private final int status;
    private final String message;
}
