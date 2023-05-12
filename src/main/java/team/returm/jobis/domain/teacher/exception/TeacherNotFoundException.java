package team.returm.jobis.domain.teacher.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class TeacherNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(GlobalErrorCode.TEACHER_NOT_FOUND);
    }
}
