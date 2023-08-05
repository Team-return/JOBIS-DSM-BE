package team.retum.jobis.domain.application.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;

public class InvalidGradeException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidGradeException();

    private InvalidGradeException() {
        super(ApplicationErrorCode.INVALID_GRADE);
    }
}
