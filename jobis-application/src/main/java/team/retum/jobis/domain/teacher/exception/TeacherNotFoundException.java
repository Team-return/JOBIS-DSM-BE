package team.retum.jobis.domain.teacher.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.teacher.exception.error.TeacherErrorCode;

public class TeacherNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(TeacherErrorCode.TEACHER_NOT_FOUND);
    }
}
