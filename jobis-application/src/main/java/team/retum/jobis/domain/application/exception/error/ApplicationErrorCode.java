package team.retum.jobis.domain.application.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplicationErrorCode implements ErrorProperty {

    INVALID_DATE(HttpStatus.BAD_REQUEST, "Invalid Date"),
    APPLICATION_STATUS_CANNOT_CHANGE(HttpStatus.BAD_REQUEST, "ApplicationEntity Status Cannot be changed"),

    INVALID_STUDENT(HttpStatus.UNAUTHORIZED, "Invalid Student"),
    INVALID_GRADE(HttpStatus.UNAUTHORIZED, "Invalid Grade"),

    FIELD_TRAIN_DATE_CANNOT_CHANGE(HttpStatus.FORBIDDEN, "Field Train Date Cannot Changed"),
    APPLICATION_CANNOT_DELETE(HttpStatus.FORBIDDEN, "ApplicationEntity Cannot Deleted"),
    APPLICATION_CANNOT_CHANGE(HttpStatus.FORBIDDEN, "Don't have permission to edit"),

    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "ApplicationEntity Not Found"),

    APPLICATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "ApplicationEntity Already Exists");

    private final HttpStatus status;
    private final String message;
}
