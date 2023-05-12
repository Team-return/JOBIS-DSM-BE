package team.returm.jobis.domain.application.exception;

import team.returm.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class ApplicationStatusCannotChangeException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationStatusCannotChangeException();

    private ApplicationStatusCannotChangeException() {
        super(ApplicationErrorCode.APPLICATION_STATUS_CANNOT_CHANGE);
    }
}
