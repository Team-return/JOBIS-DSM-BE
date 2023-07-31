package team.retum.jobis.domain.user.exception.error;

import team.retum.jobis.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorProperty {

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "invalid password"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found");

    private final HttpStatus status;
    private final String message;
}
