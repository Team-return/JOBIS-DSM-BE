package team.returm.jobis.domain.student.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class StudentAlreadyExistsException extends JobisException {

    public static final JobisException EXCEPTION =
            new StudentAlreadyExistsException();

    private StudentAlreadyExistsException() {
        super(GlobalErrorCode.STUDENT_ALREADY_EXISTS);
    }
}
