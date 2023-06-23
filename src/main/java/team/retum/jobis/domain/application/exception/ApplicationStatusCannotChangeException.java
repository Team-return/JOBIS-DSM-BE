package team.retum.jobis.domain.application.exception;

import team.retum.jobis.domain.application.exception.error.ApplicationErrorCode;
import team.retum.jobis.global.error.exception.JobisException;

public class ApplicationStatusCannotChangeException extends JobisException {
    public static final JobisException EXCEPTION = new ApplicationStatusCannotChangeException();

    private ApplicationStatusCannotChangeException() {
        super(ApplicationErrorCode.APPLICATION_STATUS_CANNOT_CHANGE);
    }
}
