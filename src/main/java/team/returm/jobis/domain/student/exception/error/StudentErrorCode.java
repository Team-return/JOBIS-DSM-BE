package team.returm.jobis.domain.student.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum StudentErrorCode implements ErrorProperty {

    STUDENT_NOT_FOUND(404, "Student Not Found"),

    STUDENT_ALREADY_EXISTS(409, "Student Already Exists");

    private final int status;
    private final String message;
}
