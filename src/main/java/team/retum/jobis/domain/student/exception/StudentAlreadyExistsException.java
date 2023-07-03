package team.retum.jobis.domain.student.exception;

import team.retum.jobis.domain.student.exception.error.StudentErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class StudentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
            new StudentAlreadyExistsException();

    private StudentAlreadyExistsException() {
        super(StudentErrorCode.STUDENT_ALREADY_EXISTS);
    }
}