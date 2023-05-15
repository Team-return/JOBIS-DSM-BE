package team.returm.jobis.domain.application.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum ApplicationErrorCode implements ErrorProperty {

    APPLICATION_STATUS_CANNOT_CHANGE(400, "Application Status Cannot be changed"),
    INVALID_DATE(400, "Invalid Date"),

    INVALID_STUDENT(401, "Invalid Student"),
    INVALID_GRADE(401, "Invalid Grade"),

    FIELD_TRAIN_DATE_CANNOT_CHANGE(403, "Field Train Date Cannot Changed"),
    APPLICATION_CANNOT_DELETE(403, "Application Cannot Deleted"),

    APPLICATION_NOT_FOUND(404, "Application Not Found"),

    APPLICATION_ALREADY_EXISTS(409, "Application Already Exists");

    private final int status;
    private final String message;
}
