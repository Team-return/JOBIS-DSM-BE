package team.returm.jobis.domain.application.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum ApplicationErrorCode implements ErrorProperty {

    INVALID_DATE(HttpStatus.BAD_REQUEST, "Invalid Date"),

    INVALID_STUDENT(HttpStatus.UNAUTHORIZED, "Invalid Student"),
    INVALID_GRADE(HttpStatus.UNAUTHORIZED, "Invalid Grade"),

    FIELD_TRAIN_DATE_CANNOT_CHANGE(HttpStatus.FORBIDDEN, "Field Train Date Cannot Changed"),
    APPLICATION_CANNOT_DELETE(HttpStatus.FORBIDDEN, "Application Cannot Deleted"),
    APPLICATION_STATUS_CANNOT_CHANGE(HttpStatus.FORBIDDEN, "Application Status Cannot be changed"),

    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Application Not Found"),

    APPLICATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Application Already Exists");

    private final HttpStatus status;
    private final String message;
}
