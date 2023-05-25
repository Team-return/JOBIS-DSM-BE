package team.returm.jobis.domain.student.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum StudentErrorCode implements ErrorProperty {

    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Student Not Found"),
    CLASSROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "Classroom Not Found"),

    STUDENT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Student Already Exists");

    private final HttpStatus status;
    private final String message;
}
