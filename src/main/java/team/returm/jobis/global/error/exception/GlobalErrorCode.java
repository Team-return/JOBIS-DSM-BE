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
    INVALID_PASSWORD(401, "invalid password"),

    USER_NOT_FOUND(404, "User Not Found"),
    TEACHER_NOT_FOUND(404, "Teacher Not Found"),
    MAIL_SEND_FAIL(404, "Mail Send Fail");

    private final Integer status;
    private final String message;
}
