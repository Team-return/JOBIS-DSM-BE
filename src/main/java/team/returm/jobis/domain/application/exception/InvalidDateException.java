package team.returm.jobis.domain.application.exception;

import team.returm.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class InvalidDateException extends JobisException {

    public static final JobisException EXCEPTION = new InvalidDateException();

    private InvalidDateException() {
        super(ApplicationErrorCode.INVALID_DATE);
    }
}
