package team.retum.jobis.domain.teacher.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum TeacherErrorCode implements ErrorProperty {

    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "Teacher Not Found");

    private final HttpStatus status;
    private final String message;
}
