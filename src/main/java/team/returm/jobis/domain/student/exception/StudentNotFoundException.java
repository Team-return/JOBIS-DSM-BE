package team.returm.jobis.domain.student.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class StudentNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new StudentNotFoundException();

    private StudentNotFoundException() {
        super(GlobalErrorCode.STUDENT_NOT_FOUND);
    }
}
