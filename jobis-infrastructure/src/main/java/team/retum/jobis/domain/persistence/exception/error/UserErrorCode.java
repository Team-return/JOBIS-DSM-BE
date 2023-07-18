package team.retum.jobis.domain.persistence.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.retum.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorProperty {

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "invalid password"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found");

    private final HttpStatus status;
    private final String message;
}
