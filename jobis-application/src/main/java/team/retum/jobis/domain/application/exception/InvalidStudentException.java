package team.retum.jobis.domain.application.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;

public class InvalidStudentException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidStudentException();

    private InvalidStudentException() {
        super(ApplicationErrorCode.INVALID_STUDENT);
    }
}
