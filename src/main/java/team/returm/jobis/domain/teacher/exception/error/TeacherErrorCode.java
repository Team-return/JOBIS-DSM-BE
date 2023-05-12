package team.returm.jobis.domain.teacher.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum TeacherErrorCode implements ErrorProperty {

    TEACHER_NOT_FOUND(404, "Teacher Not Found");

    private final Integer status;
    private final String message;
}
