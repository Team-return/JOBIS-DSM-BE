package team.retum.jobis.domain.student.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.student.exception.error.StudentErrorCode;

public class StudentNotFoundException extends JobisException {
    public static final JobisException EXCEPTION = new StudentNotFoundException();

    private StudentNotFoundException() {
        super(StudentErrorCode.STUDENT_NOT_FOUND);
    }
}
