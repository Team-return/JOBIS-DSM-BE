package team.returm.jobis.domain.user.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorProperty {

    INVALID_PASSWORD(401, "invalid password"),

    USER_NOT_FOUND(404, "User Not Found");

    private final int status;
    private final String message;
}
