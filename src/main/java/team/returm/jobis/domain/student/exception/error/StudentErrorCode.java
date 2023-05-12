package team.returm.jobis.domain.student.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum StudentErrorCode implements ErrorProperty {

    BAD_EMAIL(400, "Bad Email Domain"),

    UNVERIFIED_EMAIL(401, "Unverified Email"),
    BAD_AUTH_CODE(401, "Bad Auth Code"),

    STUDENT_NOT_FOUND(404, "Student Not Found"),

    STUDENT_ALREADY_EXISTS(409, "Student Already Exists");

    private final Integer status;
    private final String message;
}
