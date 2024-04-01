package team.retum.jobis.domain.student.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.student.exception.error.StudentErrorCode;

public class StudentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
        new StudentAlreadyExistsException();

    private StudentAlreadyExistsException() {
        super(StudentErrorCode.STUDENT_ALREADY_EXISTS);
    }
}
